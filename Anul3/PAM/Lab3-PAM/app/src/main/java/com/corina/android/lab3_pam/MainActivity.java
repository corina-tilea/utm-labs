package com.corina.android.lab3_pam;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private String pathToCaptcha;

    public static final String USER_AGENT="Mozilla/5.0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }
        verifyStoragePermissions(this);


        //pathToCaptcha=myDownloadImage();
        Bitmap bmp =getBitmapFromURL("https://www.orangetext.md/captcha/40437294810?q=5f95b89b-8585-4282-bc74-4630336f8fb3");
        if(bmp!=null){
            Log.i("info", "#BMP Not null");
        }else{
            Log.i("info", "#BMP is null");
        }
        ImageView imageViewCapthcha=(ImageView)findViewById(R.id.image_view_captcha);
        imageViewCapthcha.setImageBitmap(getResizedBitmap(bmp, 35, 90));


        Log.i("info", "#Image setted=");
        EditText editText=(EditText)findViewById(R.id.edit_text_captcha);
        final String captchaTxt=editText.getText().toString();

        Button btnSendMsg=(Button)findViewById(R.id.btn_send_msg);
        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("info", "#captchaPath="+pathToCaptcha);
                mySendPost(captchaTxt);
            }
        });


    }

    public Bitmap getBitmapFromURL(String src) {
        HttpURLConnection connection=null;
        Bitmap myBitmap=null;
        try {
            java.net.URL url = new java.net.URL(src);
            connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream input = new BufferedInputStream(connection.getInputStream());

            //InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
            input.reset();
            input.close();


        } catch (IOException e) {
            Log.i("info", "OOOPS");
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return myBitmap;
    }

    /*public String myDownloadImage(){

        try{
        URL url = new URL("https://www.orangetext.md/captcha/89594092297?q=7677158b-f244-4b91-8830-ecc86e15b36b");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        File SDCardRoot = Environment.getExternalStorageDirectory().getAbsoluteFile();
        String filename="downloadedFile.png";
        Log.i("Local filename:",""+filename);
        File file = new File(SDCardRoot,filename);
        if(file.createNewFile()){
            file.createNewFile();
        }
        FileOutputStream fileOutput = new FileOutputStream(file);
        InputStream inputStream = urlConnection.getInputStream();
        int totalSize = urlConnection.getContentLength();
        int downloadedSize = 0;
        byte[] buffer = new byte[1024];
        int bufferLength = 0;
        while ( (bufferLength = inputStream.read(buffer)) > 0 )
        {
            fileOutput.write(buffer, 0, bufferLength);
            downloadedSize += bufferLength;
            Log.i("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;
        }
        fileOutput.close();
        if(downloadedSize==totalSize) pathToCaptcha=file.getPath();
    }
    catch (MalformedURLException e){
        e.printStackTrace();
    }
    catch (IOException e){
        pathToCaptcha=null;
        e.printStackTrace();
    }
    Log.i("filepath:"," "+pathToCaptcha) ;
    return pathToCaptcha;
    }

   */ public void mySendPost(String captchaValue) {

        String url = "https://www.orangetext.md/en";
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        // add header
        post.addHeader("Host", "www.orangetext.md");
        //post.addHeader("Content-Length", "99");
        post.addHeader("Cache-Control", "max-age=0");
        post.addHeader("Origin", "https://www.orangetext.md");
        post.addHeader("Upgrade-Insecure-Requests", "1");
        post.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
        post.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        post.addHeader("Referer", "https://www.orangetext.md/en");
        post.addHeader("Accept-Encoding", "gzip, deflate, br");
        post.addHeader("Accept-Language", "ro,en-US;q=0.8,en;q=0.6,bg;q=0.4,ru;q=0.2,tr;q=0.2,de;q=0.2");
        post.addHeader("Cookie", "__utmz=65202288.1512314661.2.2.utmccn=(referral)|utmcsr=google.com|utmcct=/|utmcmd=referral; ASP.NET_SessionId=cobte2an240mf0n0drwakeo1; TS014af75e=010a2e696976024344dc578189669b7d6411bb0621f44211ad4746aaf34435e6b602ce8136b4f7638bbb195713c2c690c169f2135c; TS014af75e_28=011062b09a63ce2113641ce60de77b662fe575d891fb26c41167da58039ba5d599a8ac8878fe7e2b5806ced45260fa99fd9deada55; __utma=65202288.2132106410.1512308685.1512375717.1512470448.8; __utmb=65202288; __utmc=65202288");
    //Keep aliveqd

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
       // urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
        //urlParameters.add(new BasicNameValuePair("cn", ""));
        urlParameters.add(new BasicNameValuePair("TokenId", "40437294810"));
        //urlParameters.add(new BasicNameValuePair("locale", "en-US"));
        urlParameters.add(new BasicNameValuePair("From", "corina"));
        urlParameters.add(new BasicNameValuePair("Msisdn", "060530596"));
        urlParameters.add(new BasicNameValuePair("rest", "124"));
        urlParameters.add(new BasicNameValuePair("Message", "Test From APP"));
        urlParameters.add(new BasicNameValuePair("Captcha", captchaValue));
        urlParameters.add(new BasicNameValuePair("btnOK", "Send+SMS"));

        try {
            Log.i("info", "# TRY 1");
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            Log.i("info", "# TRY 1.1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;
        try {
            Log.i("info", "# TRY 2");
            response = client.execute(post);
            Log.i("info", "# TRY 2 after execute post");
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode() + ", " + response.getAllHeaders()[0].getValue().toString() + ", " + response.getStatusLine().getReasonPhrase());
            Log.i("info", "# TRY 2," + "Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = null;

            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));


            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();

        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, 100, 40, matrix, false);

        return resizedBitmap;
    }


    public static void verifyStoragePermissions(Activity activity) {
            // Check if we have write permission
            int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }
        }




    /*public String sendPost(final String request, final String postData) throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyManagementException {
        String result = null;
        SSLContext sslContext = SSLContext.getInstance("SSL");

        // set up a TrustManager that trusts everything
        sslContext.init(null, new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers =============");
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
                System.out.println("checkClientTrusted =============");
            }

            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
                System.out.println("checkServerTrusted =============");
            }
        } }, new SecureRandom());

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(new SSLSocketFactory(sslContext)).build();
        HttpPost httpPost = new HttpPost(request);
        ByteArrayEntity postDataEntity = new ByteArrayEntity(postData.getBytes());
        httpPost.setEntity(postDataEntity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return result;

    }
    */

}
