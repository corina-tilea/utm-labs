package com.corina.android.lab1_pam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ViewPhotoActivity extends AppCompatActivity {
    public static String CAPTURED_IMAGE_KEY="captured-image";

    private Intent mShareIntent;
    private ShareActionProvider mShareActionProvider;
    Uri file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        //Get Data from intent
        Bundle dataFromIntent=getIntent().getExtras();
        file=(Uri)dataFromIntent.get(CAPTURED_IMAGE_KEY);
        ImageView imageView=(ImageView)findViewById(R.id.imageview);
        imageView.setImageURI(file);
        //Add Home Buton Listener
        Button btnGoogleIt = (Button) findViewById(R.id.btn_home);
        btnGoogleIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnHomeClicked(v);
            }
        });
        //Init share Intent
        initShareIntent();

    }

    public void onBtnHomeClicked(View v) {
            Intent intentMainActivity = new Intent(this, MainActivity.class);
            startActivity(intentMainActivity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem itemShare =(MenuItem) menu.findItem(R.id.menu_item_share);
        // Fetch and store ShareActionProvider
        mShareActionProvider =  (ShareActionProvider) MenuItemCompat.getActionProvider(itemShare);
        setShareIntent(mShareIntent);
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    public void initShareIntent(){
        mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("image/*");
        mShareIntent.putExtra(Intent.EXTRA_STREAM, file);
    }
}
