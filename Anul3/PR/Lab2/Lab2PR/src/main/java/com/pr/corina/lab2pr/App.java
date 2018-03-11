package com.pr.corina.lab2pr;

import com.pr.corina.lab2pr.entities.Category;
import com.pr.corina.lab2pr.entities.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Hello world!
 *
 */
public class App {
    
    
    private static int checked=0;
    private static List<Order>orderList;
    private static List<Category>categoryList;
    
    public static void main( String[] args ){
        
    }
    
    public static void initApp(String dateFrom, String dateTo, Boolean loadFromURL){
        orderList=AppHttpClient.executeGetMethodRequest(Constants.URL_ORDERS, "?start="+dateFrom+"&end="+dateTo, loadFromURL);
        
        List<Category>categoryListInitial=AppHttpClient.executeGetMethodRequest(Constants.URL_CATEGORIES, "", loadFromURL);
       
        aggregateSumsToCategories(orderList, categoryListInitial);
        categoryList=new ArrayList();
        initCategoryList(categoryListInitial, categoryList);
        System.out.println("Final Category List="+categoryList.toString());
        checked=0;
    }
    
    public static void aggregateSumsToCategories(List<Order>orderList, List<Category>categoryList){
        for(Order order:orderList){
            for(Category category:categoryList){
                if(category.getId()==order.getCategoryId()){
                    category.addToTotal(order.getTotal());
                    break;
                }
            }
        }
    }
    
    public static void initCategoryList(List<Category>categoryListInitial, List<Category>categoryList){
        System.out.println("Init Category List, checked="+checked);
        System.out.println("List="+categoryList.toString());
        for(Category category:categoryListInitial){
            Category compareCategory=new Category();
            compareCategory.setId(category.getCategoryId());//parent category
            if(!category.isChecked() && category.getCategoryId()==0){
                categoryList.add(category);
                category.setChecked(Boolean.TRUE);
                checked++;
            }else if(!category.isChecked()){
                iterateList(categoryList, category, compareCategory);
                
            }
            
            
        }
         if(checked<categoryListInitial.size())
             initCategoryList(categoryListInitial, categoryList);
        
    }
    
    public static void iterateList(List<Category>categoryListToIterate, Category subCategory, Category parentCategory){
        System.out.println("Iterate List, checked="+checked);
        for(Category category:categoryListToIterate){
            if(category.getId()==subCategory.getCategoryId()){
                category.getCategoryList().add(subCategory);
                category.addToTotal(subCategory.getTotal());
                checked++;
                subCategory.setChecked(Boolean.TRUE);
                break;
            }else if(!category.getCategoryList().isEmpty()){
                iterateList(category.getCategoryList(), subCategory, parentCategory);
            }
        }
        
    
    }
    
    public static void initCategoryMap(Map<Category, List<Category>>categoryMap, List<Category>categoryList){
        System.out.println("Init Category Map, checked="+checked);
        System.out.println("Map="+categoryMap.toString());
            
         for(Category category:categoryList){
             Category compareCategory=new Category();
             compareCategory.setId(category.getCategoryId());//parent category
            if(!category.isChecked() && category.getCategoryId()==0){
                categoryMap.put(category, new ArrayList());
                category.setChecked(Boolean.TRUE);
                checked++;
            }else if(!category.isChecked() && categoryMap.containsKey(compareCategory)){
                categoryMap.get(compareCategory).add(category);
                category.setChecked(Boolean.TRUE);
                checked++;
            }
            
        }
         if(checked<categoryList.size())
             initCategoryMap(categoryMap, categoryList);
         
    }

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static void setOrderList(List<Order> orderList) {
        App.orderList = orderList;
    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    public static void setCategoryList(List<Category> categoryList) {
        App.categoryList = categoryList;
    }
    
    
    
}




// public static void main( String[] args ){
//        
//        
//        
//        FileReader reader = null;
//        try {
//            /// Read CSV
//            CsvConfiguration csvConfiguration = new CsvConfiguration();
//            csvConfiguration.setFieldDelimiter(',');
//            Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration,Category.class).createDeserializer();
//            reader = new FileReader("/home/corina/git-repository/utm-labs/Anul3/PR/Lab2/Lab2PR/src/main/resources/url_orders.txt");
//            deserializer.open(reader);
//            try{
//                deserializer.next();
//            }catch(Exception ex){}
//            while (deserializer.hasNext()) {
//                Category category = deserializer.next();
//                System.out.println(category);
//            }       deserializer.close(true);
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            
//            try {  
//                reader.close();
//            } catch (IOException ex) {
//                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }