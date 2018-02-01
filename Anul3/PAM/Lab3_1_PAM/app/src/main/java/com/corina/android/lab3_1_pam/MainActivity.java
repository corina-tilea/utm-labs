package com.corina.android.lab3_1_pam;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //http://zugo.md/rssfeed.xml

    final static String urlAddress="https://news.yam.md/ro/rss";
    private Toolbar toolbar;
    private FloatingActionButton fab, saveFAB , loadSavedArticlesFAB, clearSavedArticlesFAB, loadRssListFAB;
    private Button addFeedBtn;
    private EditText editTextFeed;
    private ListView lv;
    private File articlesFile;
    private ArticlesContent articlesContent;
    private ListView listViewUrls;
    public List<String> urlsList;
    public String urlString;

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
        saveFAB= (FloatingActionButton) findViewById(R.id.saveArticles);
        saveFAB.setOnClickListener(this);
        loadSavedArticlesFAB=(FloatingActionButton) findViewById(R.id.loadSavedArticles);
        loadSavedArticlesFAB.setOnClickListener(this);
        clearSavedArticlesFAB=(FloatingActionButton) findViewById(R.id.clearSavedArticles);
        clearSavedArticlesFAB.setOnClickListener(this);
        loadRssListFAB=(FloatingActionButton)findViewById(R.id.loadRssList);
        loadRssListFAB.setOnClickListener(this);
        addFeedBtn=(Button)findViewById(R.id.addFeedBtn);
        addFeedBtn.setOnClickListener(this);
        editTextFeed=(EditText)findViewById(R.id.editTextFeed);
        articlesFile=new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/articles.xml");
        articlesContent=new ArticlesContent();
        articlesContent= ArticlesContentSerializator.readXML(articlesFile);

        listViewUrls=new ListView(this);
        urlsList=new ArrayList<>();
        urlsList.add(urlAddress);


        if(articlesContent.getArticlesList().size()>0) {
            lv.setAdapter(new CustomAdapter(MainActivity.this, articlesContent.getArticlesList()));
        } else {
            new FeedDownloader(MainActivity.this,urlsList,lv).execute();
            Toast.makeText(getApplicationContext(),"Up to date",Toast.LENGTH_SHORT).show();
        }
    }
    public void initUrlsListFromFile(){
        if(articlesContent!=null){
            urlsList=new ArrayList<>();
            for(Article article:articlesContent.getArticlesList()){
                String urlRss=article.getLink().substring(0,article.getLink().indexOf(".md")+3);
                if(urlRss.isEmpty()){
                    urlRss=article.getLink().substring(0,article.getLink().indexOf(".ro")+3);
                }
                if(urlRss.isEmpty()){
                    urlRss=article.getLink().substring(0,article.getLink().indexOf(".com")+4);
                }
                if(urlRss.isEmpty()){
                    urlRss=article.getLink().substring(0,article.getLink().indexOf(".ru")+5);
                }
                if(urlRss.isEmpty()){
                    urlRss=article.getLink().substring(0,article.getLink().indexOf(".")+3);
                }
                if(!urlsList.contains(urlRss)){
                    urlsList.add(urlRss);
                }
            }
        }
    }

    public void loadRssAction(View view){
        initAdapterAndItemClick();
        showDialogListView(view);
    }
    public void initAdapterAndItemClick(){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.listview,R.id.txtitems, urlsList);
        listViewUrls.setAdapter(adapter);
        listViewUrls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                urlString = (String) parent.getItemAtPosition(position);
            }
        });
    }

    public void showDialogListView(View view){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        final AlertDialog alertd = builder.create();
        builder.setCancelable(false);
        builder.setView(listViewUrls);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.cancel();
                alertd.dismiss();
            }

        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    public void saveNewFeed(){
        String feedLink=editTextFeed.getText().toString();
        if(!feedLink.isEmpty() && !urlsList.contains(feedLink)){
            urlsList.add(feedLink);
            editTextFeed.setText("");
            Toast.makeText(getApplicationContext(),"Feed Saved!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:
                new FeedDownloader(MainActivity.this,urlsList,lv).execute();
                Toast.makeText(getApplicationContext(),"Up to date",Toast.LENGTH_SHORT).show();
                break;
            case R.id.saveArticles:
                ArticlesContentSerializator.writeXML(new ArticlesContent(CustomAdapter.getSavedArticles()),articlesFile);
                Toast.makeText(getApplicationContext(), "Data was stored", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loadSavedArticles:
                articlesContent= ArticlesContentSerializator.readXML(articlesFile);
                lv.setAdapter(new CustomAdapter(MainActivity.this, articlesContent.getArticlesList()));
                Toast.makeText(getApplicationContext(), "Stored data was loaded", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearSavedArticles:
                ArticlesContentSerializator.writeXML(new ArticlesContent(),articlesFile);
                Toast.makeText(getApplicationContext(), "Stored data was deleted", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loadRssList:
                loadRssAction(v);
                Toast.makeText(getApplicationContext(), "Load Rss Feed List", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addFeedBtn:
                saveNewFeed();
                break;

        }
    }
}

