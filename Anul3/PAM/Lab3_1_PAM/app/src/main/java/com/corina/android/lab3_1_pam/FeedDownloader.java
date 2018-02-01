package com.corina.android.lab3_1_pam;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by corina on 20.12.2017.
 */

public class FeedDownloader extends AsyncTask<Void,Void,Object> {

    Context c;
    List<String> urlAddress;
    ListView lv;

    public FeedDownloader(Context c, List<String> urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected Object doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(Object data) {
        super.onPostExecute(data);
        if (data.toString().startsWith("Error")) {
            Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show();
        } else {
            //PARSE RSS
            Log.i("info", "*****"+data.toString());
            List<InputStream>isList=(List<InputStream>)data;
            for(InputStream is:isList) {
                new MyRSSParser(c, (InputStream) is, lv).execute();
            }
        }
    }

    private Object downloadData() {
        String errorReturn="";
        List<InputStream>isList=new ArrayList<>();
        for (String strUlr : urlAddress) {
            Object connection = Connector.connect(strUlr);
            if (connection.toString().startsWith("Error")) {
                return connection.toString();
            }
            try {
                HttpURLConnection con = null;
                con = (HttpURLConnection) connection;
                int responseCode = con.getResponseCode();
                if (responseCode == con.HTTP_OK) {
                    InputStream is = new BufferedInputStream(con.getInputStream());
                    isList.add(is);
                }
                errorReturn= ErrorTracker.RESPONSE_EROR + con.getResponseMessage();
            } catch (IOException e) {
                e.printStackTrace();
                errorReturn= ErrorTracker.IO_EROR;
            }
        }
        if(!isList.isEmpty()){
            return isList;
        }else{
            return errorReturn;
        }
    }



}
