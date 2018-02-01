package com.corina.android.lab1_pam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_push_notification);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification(v);
            }
        });


        Button btnGoogleIt = (Button) findViewById(R.id.btn_google_it);
        btnGoogleIt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                searchInGoogle(v);
            }
        });

        Button btnOpenCamera = (Button) findViewById(R.id.btn_open_camera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCamera(v);
            }
        });

    }

    public void showNotification(View view){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10 Seconds
                Context context = getApplicationContext();
                CharSequence text = "Here is my notification, with 10 seconds delay :) !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }, 1000);
    }

    public void searchInGoogle(View view){

        EditText tetToSearch=(EditText) findViewById(R.id.text_to_search);
        Uri uri = Uri.parse("https://www.google.com/search?q="+tetToSearch.getText());
        Intent gSearchIntent = new Intent(Intent.ACTION_VIEW, uri);
        this.startActivity(gSearchIntent);

    }

    public void openCamera(View view){
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");

        RadioGroup radioGroupCamType=(RadioGroup)findViewById(R.id.radio_group_camera_type);
       // if(radioGroupCamType.getCheckedRadioButtonId()==R.id.radio_option_front_cam)
         //   intent.putExtra("android.intent.extras.CAMERA_FACING", 1);


         startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


    }
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.i("INFO", "Storage Dir="+storageDir.getAbsolutePath());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
                Intent intentViewCapturedPhoto = new Intent(this, ViewPhotoActivity.class);
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                intentViewCapturedPhoto.putExtra(ViewPhotoActivity.CAPTURED_IMAGE_KEY,imageBitmap);

            startActivity(intentViewCapturedPhoto);
            //String result = data.toURI();
            // ...
        }
    }
}

/**<Button
android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Push Notification"
        android:id="@+id/btn_push_notification"
        android:layout_alignParentTop="true"
        android:layout_alignParentStart="true" /> */