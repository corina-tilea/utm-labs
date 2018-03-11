/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab2pr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author corina
 */
public class AppHttpClient {
    
  
    
    public static List executeGetMethodRequest(String url, String params, Boolean loadFromUrl){
        InputStream responseStream=null;
        List list=new ArrayList();
        //Reader reader = null;
        try {
            
            CloseableHttpClient httpclient = HttpClients.createDefault();
    
            String urlRequest=url;
            if(url.equals(Constants.URL_ORDERS))
                urlRequest+=params;
            
            HttpGet httpGet = new HttpGet(urlRequest);
            httpGet.setHeader("x-api-key", Constants.AUTH_KEY);
            httpGet.setHeader("Accept", Constants.ACCEPT);
            
            
            CloseableHttpResponse response = httpclient.execute(httpGet);
            responseStream=response.getEntity().getContent();
            File file=null;
            InputStream inputStreamFromFile=null;
            switch(url){
                case Constants.URL_CATEGORIES:
                     file=new File(Constants.FILE_CATEGORIES);
                     if(loadFromUrl|| !file.exists() || file.length()==0 ){
                         InputStreamFile.convertInputStreamToFile(responseStream, Constants.FILE_CATEGORIES);
                     }
                     inputStreamFromFile = new FileInputStream(file);
                     CsvReader.readCsvCategories(inputStreamFromFile, list);
                     break;
                    
                case Constants.URL_ORDERS:
                     file=new File(Constants.FILE_ORDERS);
                     if(loadFromUrl|| !file.exists() || file.length()==0){
                         InputStreamFile.convertInputStreamToFile(responseStream, Constants.FILE_ORDERS);
                    }
                    inputStreamFromFile = new FileInputStream(file);
                    CsvReader.readCsvOrders(inputStreamFromFile, list); 
                   
                    break;
            }
            
            response.close();
            responseStream.close();
            
        }   catch (IOException ex) {
            Logger.getLogger(AppHttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    
}
