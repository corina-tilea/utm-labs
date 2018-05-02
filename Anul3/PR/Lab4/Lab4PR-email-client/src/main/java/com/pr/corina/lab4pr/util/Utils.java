/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab4pr.util;

/**
 *
 * @author corina
 */
public class Utils {
    
    public static String getBeginOfText(String text){
        String begin="";
        if(text!=null){
            if(text.length()>180){
                begin=text.substring(0,180);
            }else{
                begin=text.substring(0,text.length()-1);
            }
        }
        return begin;
    }
    
}
