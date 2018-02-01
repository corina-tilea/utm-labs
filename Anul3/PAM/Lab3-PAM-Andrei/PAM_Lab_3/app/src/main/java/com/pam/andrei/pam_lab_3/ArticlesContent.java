package com.pam.andrei.pam_lab_3;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 09.01.2018.
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


