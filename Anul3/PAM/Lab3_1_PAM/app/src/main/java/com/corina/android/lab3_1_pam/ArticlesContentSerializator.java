package com.corina.android.lab3_1_pam;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by corina on 20.12.2017.
 */

public class ArticlesContentSerializator {


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
