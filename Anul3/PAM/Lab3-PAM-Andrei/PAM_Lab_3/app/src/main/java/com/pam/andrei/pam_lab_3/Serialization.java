package com.pam.andrei.pam_lab_3;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by andrei on 09.01.2018.
 */

public class Serialization {


        public static void writeXML(ArticlesContent articlesList, File eventsFile){
            Serializer serializer = new Persister();
            try {
                serializer.write(articlesList, eventsFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static  ArticlesContent readXML(File eventsFile){
            Serializer serializer = new Persister();
            ArticlesContent articlesList=null;
            try {
                articlesList= serializer.read(ArticlesContent.class, eventsFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(articlesList==null) {
                articlesList = new ArticlesContent();
                articlesList.setArticlesList(new ArrayList<Article>());
            }
            return articlesList;
        }

}
