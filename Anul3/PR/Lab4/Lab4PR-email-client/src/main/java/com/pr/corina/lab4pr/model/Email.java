/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab4pr.model;

import java.io.Serializable;
import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author corina
 */
public class Email implements Serializable{
    
    private String recipient;
    private String cc;
    private String subject;
    private String body;
    private MultipartFile fileAttach;

    public Email() {
    }

    public MultipartFile getFileAttach() {
        return fileAttach;
    }

    public void setFileAttach(MultipartFile fileAttach) {
        this.fileAttach = fileAttach;
    }

    
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.recipient);
        hash = 79 * hash + Objects.hashCode(this.cc);
        hash = 79 * hash + Objects.hashCode(this.subject);
        hash = 79 * hash + Objects.hashCode(this.body);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        if (!Objects.equals(this.recipient, other.recipient)) {
            return false;
        }
        if (!Objects.equals(this.cc, other.cc)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Email{" + "recipient=" + recipient + ", cc=" + cc + ", subject=" + subject + ", body=" + body + ", file=" + fileAttach + '}';
    }

   
}
