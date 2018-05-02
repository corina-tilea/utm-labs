/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab4pr.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;

/**
 *
 * @author corina
 */
public class MessageWrapper implements Serializable{
    
    private Message message;
    private int nrOfFiles;
    private String beginText;
    private String text;
    private List<File>attachedFiles;
    
    public MessageWrapper() {
        attachedFiles=new ArrayList();
    }

    public Message getMessage() {
        return message; 
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getNrOfFiles() {
        return nrOfFiles;
    }

    public void setNrOfFiles(int nrOfFiles) {
        this.nrOfFiles = nrOfFiles;
    }

    public String getBeginText() {
        return beginText;
    }

    public void setBeginText(String beginText) {
        this.beginText = beginText;
    }

    public List<File> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(List<File> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }
    
    public void addFileToList(File f){
        this.attachedFiles.add(f);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
}
