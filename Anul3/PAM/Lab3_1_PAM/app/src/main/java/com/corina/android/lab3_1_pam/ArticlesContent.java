package com.corina.android.lab3_1_pam;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;

/**
 * Created by corina on 21.12.2017.
 */

public class ArticlesContent {

    @ElementList
    private ArrayList<Article> articlesList;

    public ArticlesContent(){

    }
    public ArticlesContent(ArrayList<Article> list){
        this.articlesList=list;
    }

    public ArrayList<Article> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(ArrayList<Article> articlesList) {
        this.articlesList = articlesList;
    }
}


