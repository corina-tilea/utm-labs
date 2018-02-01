package com.pam.andrei.pam_lab_3;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final static String urlAddress="https://news.yam.md/ro/rss";
    private Toolbar toolbar;
    private FloatingActionButton fab, save , loadSavedArticles, clearSavedArticles;
    private ListView lv;
    private File articlesFile;
    private ArticlesContent articlesContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initComponents();

        for(int i=0;i<lv.getChildCount();i++){
            lv.getChildAt(i).setBackgroundColor(Color.WHITE);
        }

    }


    private void initComponents(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv= (ListView) findViewById(R.id.lv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        save= (FloatingActionButton) findViewById(R.id.saveArticles);
        save.setOnClickListener(this);
        loadSavedArticles=(FloatingActionButton) findViewById(R.id.loadSavedArticles);
        loadSavedArticles.setOnClickListener(this);
        clearSavedArticles=(FloatingActionButton) findViewById(R.id.clearSavedArticles);
        clearSavedArticles.setOnClickListener(this);
        articlesFile=new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/articles.xml");
        articlesContent=new ArticlesContent();
        articlesContent= Serialization.readXML(articlesFile);

        if(articlesContent.getArticlesList().size()>0) {
            lv.setAdapter(new CustomAdapter(MainActivity.this, articlesContent.getArticlesList()));
            //Toast.makeText(getApplicationContext(), "STORED", Toast.LENGTH_SHORT).show();
        } else {
            new Downloader(MainActivity.this,urlAddress,lv).execute();
            Toast.makeText(getApplicationContext(),"Up to date",Toast.LENGTH_SHORT).show();
        }





    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:
                new Downloader(MainActivity.this,urlAddress,lv).execute();
                Toast.makeText(getApplicationContext(),"Up to date",Toast.LENGTH_SHORT).show();
                break;
            case R.id.saveArticles:
                Serialization.writeXML(new ArticlesContent(CustomAdapter.getSavedArticles()),articlesFile);
                Toast.makeText(getApplicationContext(), "Data was stored", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loadSavedArticles:
                articlesContent= Serialization.readXML(articlesFile);
                lv.setAdapter(new CustomAdapter(MainActivity.this, articlesContent.getArticlesList()));
                Toast.makeText(getApplicationContext(), "Stored data was loaded", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearSavedArticles:
                Serialization.writeXML(new ArticlesContent(),articlesFile);
                Toast.makeText(getApplicationContext(), "Stored data was erased", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

