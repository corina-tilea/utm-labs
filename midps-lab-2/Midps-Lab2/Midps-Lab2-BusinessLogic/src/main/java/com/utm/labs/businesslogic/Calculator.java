/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utm.labs.businesslogic;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Calculator {
    public static final char ADDITION='+';
    public static final char SUBTRACTION='-';
    public static final char DIVISION='/';
    public static final char MULTIPLICATION='*';
    public static final char POWER='^';
    public static final char NONE='?';
    
     public static ArrayList<String> getParameters(StringBuilder sb, String sign){
        ArrayList<String> parameters=new ArrayList<String>() ;
            int indexSign=sb.lastIndexOf(sign);
        parameters.add(sb.substring(0,indexSign).trim());
        parameters.add(sb.substring(indexSign+1,sb.length()).trim());
        return parameters;
    }
     
     public static int performIntComputations(String firstParam,String secondParam , char sign){
        int first=Integer.parseInt(firstParam);
        int second=Integer.parseInt(secondParam);
        int result=0;
        switch(sign){
            case Calculator.ADDITION:
                result=first+second;
                break;
            case Calculator.SUBTRACTION:
                result=first-second;
                break;
            case Calculator.MULTIPLICATION:
                result=first*second;
                break;
            case Calculator.DIVISION:
                result=first/second;
                break;
        }
        return result;
    }

    public static float compute(String firstParam,String secondParam , char sign){
        System.out.println("FIRST PARAM = "+firstParam);
        System.out.println("SECOND PARAM = "+secondParam);
        float first=Float.parseFloat(firstParam);
        float second=Float.parseFloat(secondParam);
        float result=0;
        switch(sign){
            case Calculator.ADDITION:
                result=first+second;
                break;
            case Calculator.SUBTRACTION:
                result=first-second;
                break;
            case Calculator.MULTIPLICATION:
                result=first*second;
                break;
            case Calculator.DIVISION:
                result=first/second;
                break;
            case Calculator.POWER:
                result=(float) Math.pow(first, second);
        }
        return result;

    }
    
    public static char getOperation(StringBuilder sb){
         for (int i=sb.length()-1 ; i>0 ;i--){
             char sign = sb.charAt(i);
             switch(sign){
                 case Calculator.ADDITION:
                     return Calculator.ADDITION;
                 case Calculator.SUBTRACTION:
                     return Calculator.SUBTRACTION;
                 case Calculator.DIVISION:
                     return Calculator.DIVISION;
                 case Calculator.MULTIPLICATION:
                     return Calculator.MULTIPLICATION;
                 case Calculator.POWER:
                     return Calculator.POWER;
             }

         }
         return Calculator.NONE;
    }
    
    private int processInputLine(StringBuilder sb) {
        for (int i = sb.length()-1; i > 0; i--) {
            char sign = sb.charAt(i);
             switch(sign){
                 case Calculator.ADDITION:
                     return i;
                 case Calculator.SUBTRACTION:
                     return i;
                
             }
        }
        return -1;
        
    }
}
