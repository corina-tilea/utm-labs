/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab4pr.util;

import com.pr.corina.lab4pr.model.Email;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 *
 * @author corina
 */
public class EmailUtil {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailUtil.class);

     public static MimeMessagePreparator getMessagePreparator(final Email email, final String from, final String filePath) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                initMessage(mimeMessage, from, email, filePath);
            }
        };
        return preparator;
    }
     
     public static void initMessage(MimeMessage mimeMessage, String from, Email email, String filePath) throws MessagingException{
                mimeMessage.setFrom(from);
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email.getRecipient()));
                
                mimeMessage.setRecipient(Message.RecipientType.CC,
                        new InternetAddress(email.getCc()));
                
                mimeMessage.setText(email.getBody());
                mimeMessage.setSubject(email.getSubject());
                Multipart multipart = new MimeMultipart();

                MimeBodyPart textBodyPart = new MimeBodyPart();
                textBodyPart.setText(email.getBody());
                multipart.addBodyPart(textBodyPart);  // add the text part
                
                if(email.getFileAttach()!=null){
                    MimeBodyPart attachmentBodyPart= new MimeBodyPart();
                    DataSource source = new FileDataSource(filePath); // ex : "C:\\test.pdf"
                    attachmentBodyPart.setDataHandler(new DataHandler(source));
                    attachmentBodyPart.setFileName(email.getFileAttach().getOriginalFilename()); // ex : "test.pdf"
                    multipart.addBodyPart(attachmentBodyPart); // add the attachement part
                }
                mimeMessage.setContent(multipart);
                
     }
     public static void prepareMessageForDraft(final Email email, final String from, final String filePath, final Folder drafts) {
         
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
              
                initMessage(mimeMessage, from, email, filePath);
                mimeMessage.setFlag(Flag.DRAFT, true); 
                Message[] msgs={mimeMessage};
                drafts.appendMessages(msgs);
                
            }
        };
    }
     
     
     /**
     * Downloads new messages and fetches details for each message.
     * @param protocol
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public void downloadEmails(String protocol, String host, String port,
            String userName, String password) {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);
 
        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            store.connect(userName, password);
 
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
 
            // fetches new messages from server
            Message[] messages = folderInbox.getMessages();
 
            for (int i = 0; i < messages.length; i++) {
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(RecipientType.CC));
                String sentDate = msg.getSentDate().toString();
 
                String contentType = msg.getContentType();
                String messageContent = "";
 
                if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (Exception ex) {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }
 
                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);
            }
 
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
    }
     /**
     * Returns a list of addresses in String format separated by comma
     *
     * @param address an array of Address objects
     * @return a string represents a list of addresses
     */
    private String parseAddresses(Address[] address) {
        String listAddress = "";
 
        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }
 
        return listAddress;
    }
 
    
      /**
     * Returns a Properties object which is configured for a POP3/IMAP server
     *
     * @param protocol either "imap" or "pop3"
     * @param host
     * @param port
     * @return a Properties object
     */
    private Properties getServerProperties(String protocol, String host,
            String port) {
        Properties properties = new Properties();
 
        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);
 
        // SSL setting
        properties.setProperty(
                String.format("mail.%s.socketFactory.class", protocol),
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(
                String.format("mail.%s.socketFactory.fallback", protocol),
                "false");
        properties.setProperty(
                String.format("mail.%s.socketFactory.port", protocol),
                String.valueOf(port));
 
        return properties;
    }
 
    public  void readEmails(String email, String password ){
         try {
             Session session = Session.getDefaultInstance(new Properties( ));
             Store store = session.getStore("imaps");
             
             store.connect("imap.googlemail.com", 993, email, password);
             logger.info("***Connection has been done!");
             
             Folder inbox = store.getFolder( "INBOX" );
             inbox.open( Folder.READ_WRITE );
             
             // Fetch unseen messages from inbox folder
             Message[] messages = inbox.search(
                     new FlagTerm(new Flags(Flags.Flag.SEEN), false));
             
             // Sort messages from recent to oldest
//             Arrays.sort( messages, ( m1, m2 ) -> {
//                 return m2.getSentDate().compareTo( m1.getSentDate() );
//             } );
              logger.info(
                         "sendDate: " + messages[0].getSentDate()
                                 + " subject:" + messages[0].getSubject() );
//             for ( Message message : messages ) {
//                 System.out.println(
//                         "sendDate: " + message.getSentDate()
//                                 + " subject:" + message.getSubject() );
//                 message.setFlag(Flags.Flag.SEEN, true);
//             }
         
         } catch (NoSuchProviderException ex) {
             Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
             
         }catch(AuthenticationFailedException ex){
             Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
             
         } catch (MessagingException ex) {
             Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public boolean testCredentials(String email, String password){
        
         try {
             Session session = Session.getDefaultInstance(new Properties( ));
             Store store = session.getStore("imaps");
             
             store.connect("imap.googlemail.com", 993, email, password);
             logger.info("***Connection has been done!");
       
         } catch (NoSuchProviderException ex) {
             Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
             
         }catch(AuthenticationFailedException ex){
             Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         } catch (MessagingException ex) {
             Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
         }
         return true;
    }
    
   
}
