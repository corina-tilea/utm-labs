package com.pam.andrei.pam_lab_3;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class CustomAdapter extends BaseAdapter {
    private Context c;
    private ArrayList<Article> articles;
    private static ArrayList<Article> savedArticles;
    public CustomAdapter(Context c, ArrayList<Article> articles) {
        this.c = c;
        this.articles = articles;
    }
    @Override
    public int getCount() {
        return articles.size();
    }
    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }
        TextView titleTxt= (TextView) convertView.findViewById(R.id.titleTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        //TextView dateTxt= (TextView) convertView.findViewById(R.id.dateTxt);
        //ImageView img= (ImageView) convertView.findViewById(R.id.articleImage);
        final Article article= (Article) this.getItem(position);
        final String title=article.getTitle();
        String desc=article.getDescription();
    //  String date=article.getDate();
    //  String imageUrl=article.getImageUrl().replace("localhost","10.0.2.2");
        titleTxt.setText(title);
        descTxt.setText(desc);
        //descTxt.setText(desc.substring(0,100));
        //dateTxt.setText(date);
        //Client.downloadImage(c, imageUrl, img);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(c,title,Toast.LENGTH_SHORT).show();
                /*if(article.getLink().length()>0){
                    Toast.makeText(c,article.getLink(),Toast.LENGTH_SHORT).show();

                }*/
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getLink()));
                c.startActivity(browserIntent);

            }
        });
        return convertView;
    }

    public static ArrayList<Article> getSavedArticles() {
        return savedArticles;
    }

    public static void setSavedArticles(ArrayList<Article> savedArticles) {
        CustomAdapter.savedArticles = savedArticles;
    }
}
