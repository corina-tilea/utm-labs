package com.corina.android.lab3_1_pam;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by corina on 20.12.2017.
 */

public class MyRSSParser extends AsyncTask<Void,Void,Boolean> {
    Context c;
    InputStream is;
    ListView lv;
    ArrayList<Article> articles=new ArrayList<>();
    public MyRSSParser(Context c, InputStream is, ListView lv) {
        this.c = c;
        this.is = is;
        this.lv = lv;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRSS();
    }
    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        if(isParsed)
        {
            //BIND
            lv.setAdapter(new CustomAdapter(c,articles));
            CustomAdapter.setSavedArticles(articles);
        }else {
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean parseRSS()
    {
        try
        {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(is,null);
            int event=parser.getEventType();
            String tagValue=null;
            Boolean isSiteMeta=true;
            articles.clear();
            Article article=new Article();
            do {
                String tagName=parser.getName();
                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if(tagName.equalsIgnoreCase("entry"))
                        {
                            article=new Article();
                            isSiteMeta=false;
                        }else if(tagName.equalsIgnoreCase("link")){
                            String link=parser.getAttributeValue(null, "href");
                            article.setLink(link);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        tagValue=parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(!isSiteMeta)
                        {
                            if(tagName.equalsIgnoreCase("title")) {
                                article.setTitle(tagValue);
                            }else if(tagName.equalsIgnoreCase("summary")) {
                                String desc=tagValue;
                                article.setDescription(desc.substring(desc.indexOf("/>")+1));
                            }
                        }
                        if(tagName.equalsIgnoreCase("entry"))
                        {
                            articles.add(article);
                            isSiteMeta=true;
                        }
                        break;
                }
                event=parser.next();
            }while (event != XmlPullParser.END_DOCUMENT);
            return true;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}


