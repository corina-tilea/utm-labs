/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pr.corina.lab2pr;

import com.pr.corina.lab2pr.entities.Category;
import com.pr.corina.lab2pr.entities.Order;
import com.pr.corina.lab2pr.utils.DoubleConverter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.Serializer;
import net.sf.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

/**
 *
 * @author corina
 */
public class CsvReader {
    
    public static List<Category> readCsvCategories(InputStream responseStream, List categoryList) throws IOException{
        
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(1, false, true));
        csvConfiguration.setFieldDelimiter(',');
        
        Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration,Category.class).createDeserializer();
        Reader reader= new InputStreamReader(responseStream);
        deserializer.open(reader);

        while (deserializer.hasNext()) {
            Category category = deserializer.next();
            categoryList.add(category);
            System.out.println(category);
        }   
        deserializer.close(true);

        //Close response - is done in the method that calls readCsvCategories
        //reader.close();
        
        return categoryList;
    }
    public static List<Order> readCsvOrders(InputStream responseStream, List orderList) throws IOException{
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(1, false, true));
        csvConfiguration.setFieldDelimiter(',');
        
        Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration,Order.class).createDeserializer();
        Reader reader= new InputStreamReader(responseStream);
        deserializer.open(reader);

     
        while (deserializer.hasNext()) {
            Order order = deserializer.next();
            order.setTotal(DoubleConverter.round(order.getTotal(), 2));
            orderList.add(order);
            System.out.println(order);
        }   
        deserializer.close(true);

        //Close response - is done in the method that calls eadCsvOrders
        //reader.close();
        
        return orderList;
    }
    
    
}
