/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab4pr.web;

import com.pr.corina.lab4pr.util.EmailUtil;
import com.pr.corina.lab4pr.model.User;
import com.pr.corina.lab4pr.validator.UserFormValidator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author corina
 */
@Controller
@SessionAttributes("userObj")
public class LoginController {
       private final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

       @Autowired
       UserFormValidator userFormValidator;
       
       EmailUtil emailUtils=new EmailUtil();
	
   
       @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/login";
	}
        
        @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goToLoginPage(Model model) {

		logger.debug("goToLoginPage()");
                User user=new User();
                model.addAttribute("userForm", user);
                
		return "email/login";

	}
        
        // save or update user
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("loginUser() : {}", user);

		if (result.hasErrors()) {
                    logger.info("HAS ERRORS!");

			return "email/login";
		} else {
                    logger.info("NO ERRORS!");
			//JavaMailSenderImpl mailSender=connectToEmail(user.getEmail(), user.getPassword());
//                        try {
//                            mailSender.send(EmailUtil.getMessagePreparator());
//                            System.out.println("Message Send...Hurrey");
//                        } catch (MailException ex) {
//                            System.err.println(ex.getMessage());
//                        }
                        
                        boolean validCredentials=emailUtils.testCredentials(user.getEmail(), user.getPassword());
                        if(validCredentials){
                            model.addAttribute("userObj", user);
                            return "redirect:/emails/";
                        }else{
                            return "email/login";
                        }
		}

	}
        
       
        
        
        public void connectToReadEmails(String email, String password){
            System.out.println("Emai="+email+", pass="+password);
            String imapUrl="imaps://"+email+":"+password+"@imap.googlemail.com:993/INBOX";
            MailReceiver receiver = new ImapMailReceiver(imapUrl);
            logger.info("receiver created");
           try {
               Message[] msgs=receiver.receive();
                logger.info("HAS ERRORS!"+msgs[0].getSubject());
               
               // for IMAP
//        String protocol = "imap";
//        String host = "imap.gmail.com";
//        String port = "993";
//
//
// 
//        EmailReceiver receiver = new EmailReceiver();
//        receiver.downloadEmails(protocol, host, port, email, password);
           } catch (MessagingException ex) {
               Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
        }

}
