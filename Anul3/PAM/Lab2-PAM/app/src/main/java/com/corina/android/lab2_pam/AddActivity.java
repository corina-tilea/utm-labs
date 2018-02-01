package com.corina.android.lab2_pam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AddActivity extends AppCompatActivity {

    private Button btnSave;
    private File eventsFile;
    private DataEvents dataEvents;
    private Event event;
    private Calendar calendar;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText denumireEventEditText;
    private String actionType;
    private Button btnDelete;

    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initComponents();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSaveEvent();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDelete();
            }
        });

    }
    public void initComponents(){
        btnSave=(Button)this.findViewById(R.id.btn_save);
        btnDelete=(Button)this.findViewById(R.id.btn_delete);


        eventsFile=new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/events.xml");
        denumireEventEditText=(EditText)findViewById(R.id.edit_text_event);
        dataEvents=new DataEvents();
        dataEvents=Utils.readXML(eventsFile);

        Bundle dataFromIntent=getIntent().getExtras();
        actionType=(String)dataFromIntent.get(Constants.ACTION_TYPE);
        if(actionType.equals(Constants.ACTION_TYPE_EDIT)){
            event=(Event)dataFromIntent.get(Constants.EVENT_SELECTED);
            calendar=event.getDateTime();
            denumireEventEditText.setText(event.getDenumire());
            btnDelete.setEnabled(true);
            btnSave.setText("Update");
        }else {
            calendar = (GregorianCalendar) dataFromIntent.get(Constants.CALENDAR_EXTRA);
            btnDelete.setEnabled(false);
            btnSave.setText("Save");
        }

        datePicker=(DatePicker)findViewById(R.id.datePicker);
        timePicker=(TimePicker)findViewById(R.id.timePicker);

        datePicker.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

          /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(AddActivity.this, CalendarAlarmReceiver.class);
        alarmIntent.putExtra(Constants.FILE_EXTRA,eventsFile);
        pendingIntent = PendingIntent.getBroadcast(AddActivity.this, 0, alarmIntent, 0);

    }

    public void resetAlarmAfterSave() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Event firstEvent=dataEvents.getEvent().get(0);
        manager.set(AlarmManager.RTC_WAKEUP, firstEvent.getDateTime().getTimeInMillis(), pendingIntent);
    }

    public void performSaveEvent(){
        String text = denumireEventEditText.getEditableText().toString();
        if(actionType.equals(Constants.ACTION_TYPE_ADD)){
            event = new Event();
            calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getHour(), timePicker.getMinute());//Calendar.getInstance();
            event.setId(dataEvents.getEvent().size() + 1);
            event.setAnnounced(false);
            event.setDenumire(text);
            event.setDateTime(calendar);
            dataEvents.getEvent().add(event);

            dataEvents.sortEvents();
            Utils.writeXML(dataEvents, eventsFile);
        }else{
            event.setDenumire(text);
            event.setDateTime(calendar);
            dataEvents.updateEventFromList(event);
            Utils.writeXML(dataEvents, eventsFile);
        }
        Toast.makeText(getApplicationContext(), "Event saved successfully!", 0).show();
        resetAlarmAfterSave();
        this.finish();
    }

    public void performDelete(){
        if(event!=null){
            dataEvents.getEvent().remove(event);
            Utils.writeXML(dataEvents, eventsFile);
            Toast.makeText(getApplicationContext(), "Event deleted Successfully!", 0).show();
            Intent intentMain=new Intent(this,MainActivity.class);
            startActivity(intentMain);
        }
    }





   // http://simple.sourceforge.net/download/stream/doc/tutorial/tutorial.php
}
