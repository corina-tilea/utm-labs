/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pr.corina.lab4pr.web;

import com.pr.corina.lab4pr.util.Utils;
import com.pr.corina.lab4pr.util.EmailUtil;
import com.pr.corina.lab4pr.model.Email;
import com.pr.corina.lab4pr.model.MessageWrapper;
import com.pr.corina.lab4pr.model.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author corina
 */
@SessionAttributes("emails")
@Controller
public class EmailController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailController.class);

    Session session;// = Session.getDefaultInstance(new Properties( ));
    Store store;// = session.getStore("imaps");
    Folder inbox;
    Folder drafts;

    @RequestMapping(value = "emails/", method = RequestMethod.GET)
    public String viewEmails(Model model, HttpServletRequest request) {
            logger.debug("viewEmails()");
            User user = (User) request.getSession().getAttribute("userObj");
            if(user!=null){
                if(session==null)
                    initSession(user.getEmail(), user.getPassword());
               
                List<MessageWrapper> emails;
                Object emailsFromSession=request.getSession().getAttribute("emails");
                if(emailsFromSession!=null){
                    emails=(List<MessageWrapper>)emailsFromSession;
                }else
                    emails=new ArrayList<>();
                        
                model.addAttribute("emails", emails);

                initUnreadMessages(model);
                
                return "email/email-list";
            }else{
                return "redirect:/login";
            }
    }

    @RequestMapping(value = "/new-email", method = RequestMethod.GET)
    public String newEmail(Model model, HttpServletRequest request) {
            User user = (User) request.getSession().getAttribute("userObj");
            if(user!=null){
                Email newEmail = new Email();
                model.addAttribute("email", newEmail);

                initUnreadMessages(model);
            
                return "email/new-email";
            }else{
                return "redirect:/login";
            }
    }


    @RequestMapping(value = {"/send-email-new"}, method = RequestMethod.POST)
    public String sendEmailNew(@Valid Email email, BindingResult result,
                               @RequestParam(value = "sendParam") String sendOrDraft, 
                               ModelMap model, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("userObj");
        if(user!=null){
            logger.info(" in uploadDocument()");
            if (result.hasErrors()) {
                logger.warn(" in uploadDocument()- warning validation errors");
                return "redirect:/new-email";
            } else {

                String filePath = "";
                if (email.getFileAttach() != null && !email.getFileAttach().isEmpty()) {
                    filePath = request.getServletContext().getRealPath("/") + email.getFileAttach().getOriginalFilename();
                    email.getFileAttach().transferTo(new File(filePath));
                    System.out.println("Fetching file" + email.getFileAttach().getSize());
                    System.out.println("Fetching file name=" + email.getFileAttach().getOriginalFilename());
                }
                JavaMailSenderImpl mailSender = connectToEmail(user.getEmail(), user.getPassword());

                if (sendOrDraft.equalsIgnoreCase("Send Email")) {
                    logger.info(" send email");
                    try {
                        logger.info("*** Email subject=" + email.getSubject());
                        mailSender.send(EmailUtil.getMessagePreparator(email, user.getEmail(), filePath));
                        logger.info("Email Sent Successfull!");

                    } catch (MailException ex) {
                        logger.error(ex.getMessage());
                    }

                } else if (sendOrDraft.equalsIgnoreCase("Save to Drafts")) {

                    try {
                        initDraftsFolder();
                        MimeMessage mimeMessage = new MimeMessage(session);
                        mimeMessage.setFlag(Flags.Flag.SEEN, true);
                        mimeMessage.setFlag(Flags.Flag.DRAFT, true);
                        EmailUtil.initMessage(mimeMessage, user.getEmail(), email, filePath);
                        Message[] msgs = {mimeMessage};
                        drafts.appendMessages(msgs);
                        drafts.close(true);

                        logger.info("Email Saved to Drafts");
                    } catch (MessagingException ex) {
                        Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return "redirect:/emails";
            }
        }else{
             return "redirect:/login";
        }
    }

    public MimeMessage createMessageForDraft(Email email, User user, String path) {
        MimeMessage mimeMessage = new MimeMessage(session);
        try {

            mimeMessage.setFrom(user.getEmail());
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(email.getRecipient()));

            mimeMessage.setRecipient(Message.RecipientType.CC,
                    new InternetAddress(email.getCc()));

            mimeMessage.setSubject(email.getSubject());
            mimeMessage.setText(email.getBody());

            mimeMessage.setFlag(Flags.Flag.DRAFT, true);
            mimeMessage.setFlag(Flags.Flag.SEEN, true);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mimeMessage;

    }

    @RequestMapping(value = "/last-emails", method = RequestMethod.POST)
    public String viewLastNEmails(@ModelAttribute("nrOfEmails") String nrOfEmails,
            BindingResult result, Model model, HttpServletRequest request,final RedirectAttributes redirectAttributes) {
            User user = (User) request.getSession().getAttribute("userObj");
            if(user!=null){
                logger.debug("viewLastNEmails() : {}", nrOfEmails);

                List<MessageWrapper> emails = readEmails(0, Integer.parseInt(nrOfEmails));
                model.addAttribute("emails", emails);

                initUnreadMessages(model);

                return "email/email-list";
            }else{
                return "redirect:/login";
            }
    }

    public void initUnreadMessages(Model model){
        try {
            int unreadMsgs = inbox.getUnreadMessageCount();
            model.addAttribute("unreadMsgs", unreadMsgs);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @RequestMapping("/view-email-details/{emailIndex}")
    public String viewEmailDetails(@PathVariable("emailIndex") Integer emailIndex, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userObj");
        if(user!=null){
            logger.debug("viewEmailDetails() : {}", emailIndex);
            List<MessageWrapper> emails = (List<MessageWrapper>) request.getSession().getAttribute("emails");
            MessageWrapper msg = emails.get(emailIndex);
            model.addAttribute("email", msg);

            initUnreadMessages(model);

            try {
                msg.getMessage().setFlag(Flags.Flag.SEEN, true);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "email/email-details";
        }else{
             return "redirect:/login";
        }
    }

    public void initDraftsFolder() {
        try {
            
            drafts = store.getFolder("[Gmail]/Drafts");
            drafts.open(Folder.READ_WRITE);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean initSession(String email, String password) {
        try {
            session = Session.getDefaultInstance(new Properties());
            store = session.getStore("imaps");

            store.connect("imap.googlemail.com", 993, email, password);
            logger.info("***Connection has been done!");

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            inbox.addMessageCountListener(new MessageCountAdapter() {

                @Override
                public void messagesAdded(MessageCountEvent event) {
                    Message[] messages = event.getMessages();

                    for (Message message : messages) {
                        try {
                            logger.info("[****]Mail Subject:- " + message.getSubject());
                            break;
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            
          

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);

        } catch (AuthenticationFailedException ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (MessagingException ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public List<MessageWrapper> readEmails(int startFrom, int quantity) {
        List<Message> partMessages = null;
        List<MessageWrapper> messageWrapper = null;
        try {

            Message[] messages = inbox.
                    search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            Message[] partMessageArr = new Message[quantity];
            partMessageArr = Arrays.copyOfRange(messages, messages.length - quantity - 1, messages.length - 1);
            List<Message> messagesList = Arrays.asList(partMessageArr);
            Collections.sort(messagesList, new Comparator<Message>() {
                public int compare(Message o1, Message o2) {
                    try {
                        if (o1.getSentDate() == null || o2.getSentDate() == null) {
                            return 0;
                        }
                        return o2.getSentDate().compareTo(o1.getSentDate());
                    } catch (MessagingException ex) {
                        Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 0;
                }
            });

            messageWrapper = convertMessagesToWrapper(messagesList);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageWrapper;

    }

    public JavaMailSenderImpl connectToEmail(String email, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(email);
        mailSender.setPassword(password);

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen

        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    
    public List<MessageWrapper> convertMessagesToWrapper(List<Message> messagesList) throws MessagingException {
        List<MessageWrapper> msgWrapperList = new ArrayList();
        for (Message msg : messagesList) {

            MimeMessage mimeMsg = (MimeMessage) msg;
            MimeMessage mimeNew = new MimeMessage(mimeMsg);
            logger.info("msgs Subject=" + mimeNew.getSubject());
            try {
                MessageWrapper msgWrapper = new MessageWrapper();
                msgWrapper.setMessage(mimeNew);
                if (mimeNew != null) {
                    if (mimeNew.isMimeType("text/plain")) {
                        String text =mimeNew.getContent().toString();
                        msgWrapper.setText(text);
                        
                        text=Utils.getBeginOfText(text);
                        msgWrapper.setBeginText(text);

                    } else if (mimeNew.isMimeType("multipart/*")) {
                        Multipart multipart = (Multipart) mimeNew.getContent();
                        for (int i = 0; i < multipart.getCount(); i++) {
                            BodyPart bodyPart = multipart.getBodyPart(i);

                            if (bodyPart.isMimeType("text/plain")) {
                                String text = bodyPart.getContent().toString();
                                msgWrapper.setText(text);
                                text=Utils.getBeginOfText(text);
                                msgWrapper.setBeginText(text);
                                
                            } else if (bodyPart.isMimeType("text/html")) {
                                String textHtml = org.jsoup.Jsoup.parse(bodyPart.getContent().toString()).toString();
                                msgWrapper.setText(textHtml);

                                String text = org.jsoup.Jsoup.parse(bodyPart.getContent().toString()).text();
                                text=Utils.getBeginOfText(text);
                                msgWrapper.setBeginText(text);

                            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                                msgWrapper.setBeginText(msgWrapper.getBeginText());

                                InputStream is = bodyPart.getInputStream();
                                File f = new File("/tmp/" + bodyPart.getFileName());
                                FileOutputStream fos = new FileOutputStream(f);
                                byte[] buf = new byte[4096];
                                int bytesRead;
                                while ((bytesRead = is.read(buf)) != -1) {
                                    fos.write(buf, 0, bytesRead);
                                }
                                fos.close();
                                msgWrapper.addFileToList(f);
                            }
                        }
                    }
                }
                msgWrapperList.add(msgWrapper);
            } catch (IOException ex) {
                Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return msgWrapperList;
    }

}

//SOULTIE IMAP PROBLEM:
//https://javaee.github.io/javamail/FAQ#imapserverbug
