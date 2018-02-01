package com.pam.andrei.pam_lab_3;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by andrei on 04.01.2018.
 */

public class Downloader extends AsyncTask<Void,Void,Object> {

    Context c;
    String urlAddress;
    ListView lv;
    public Downloader(Context c, String urlAddress, ListView lv) {
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
        if(data.toString().startsWith("Error"))
        {
            Toast.makeText(c,data.toString(),Toast.LENGTH_SHORT).show();
        }else {
            //PARSE RSS
            new RSSParser(c, (InputStream) data,lv).execute();
        }
    }
    private Object downloadData()
    {
        Object connection=Connector.connect(urlAddress);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }
        try
        {
            HttpURLConnection con=null;
            con=(HttpURLConnection) connection;
            int responseCode=con.getResponseCode();
            if(responseCode==con.HTTP_OK)
            {
                InputStream is=new BufferedInputStream(con.getInputStream());
                return is;
            }
            return ErrorTracker.RESPONSE_EROR+con.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.IO_EROR;
        }
    }

}
