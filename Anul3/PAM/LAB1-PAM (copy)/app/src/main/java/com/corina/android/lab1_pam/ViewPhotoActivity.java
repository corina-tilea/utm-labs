package com.corina.android.lab1_pam;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

public class ViewPhotoActivity extends AppCompatActivity {
    public static String CAPTURED_IMAGE_KEY="captured-image";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        //Get Data from intent
        Bundle dataFromIntent=getIntent().getExtras();
        Bitmap bitmap=(Bitmap)dataFromIntent.get(CAPTURED_IMAGE_KEY);

        BitmapDrawable background = new BitmapDrawable(bitmap);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.layout_show_image);
        gridLayout.setBackground(background);
       // ImageView imageViewCaptured=(ImageView)findViewById(R.id.image_view_captured);
        //imageViewCaptured.setImageBitmap(bitmap);


    }
}
/**<ImageView
 android:layout_width="wrap_content"
 android:layout_height="420dp"
 android:id="@+id/image_view_captured"
 android:layout_row="0"
 android:layout_column="0"
 android:maxHeight="500dp" />*/