package com.corina.android.lab2_pam;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class AlarmView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_view);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.raindrops);
        TextView textViewEventDenumire=(TextView)findViewById(R.id.text_view_event_denumire);
        TextView textViewEventData=(TextView)findViewById(R.id.text_view_event_date);
        Button btn_close_notification=(Button)findViewById(R.id.btn_close_notification);

        Bundle dataFromIntent=getIntent().getExtras();
        Event eventToShow=(Event)dataFromIntent.get(Constants.EVENT_FOR_SHOW_ALARM);

        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        textViewEventData.setText("Date time: "+formatDate.format(eventToShow.getDateTime().getTime()));

        textViewEventDenumire.setText(eventToShow.getDenumire());
        mp.start();
        btn_close_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                finish();
            }
        });

    }
}
