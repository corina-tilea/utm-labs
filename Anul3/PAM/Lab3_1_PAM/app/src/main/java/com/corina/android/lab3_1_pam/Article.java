package com.corina.android.lab3_1_pam;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by corina on 20.12.2017.
 */
@Root
public class Article implements Comparable<Article>, Serializable {
    @Element
    private String title;
    @Element
    private String description;
    @Element
    private String link;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int compareTo(Article a) {
        return getTitle().compareTo(a.getTitle());
    }
}
