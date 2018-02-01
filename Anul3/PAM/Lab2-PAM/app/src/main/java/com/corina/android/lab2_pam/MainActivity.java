package com.corina.android.lab2_pam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button btnAdd;
    private Button btnSearch;
    private TextView textViewToShearch;
    private Calendar calendar;

    private ListView listViewEvents;
    private Event event;
    private File eventsFile;
    private DataEvents dataEvents;
    private List<String> eventsStrFound;
    private List<Event>foundEvents;
    private int selectedItem;

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddEventIntent(null);
                startAt();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch(v);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendar=new GregorianCalendar(year, month, dayOfMonth);
                performSearchByDate(view, year, month, dayOfMonth);

            }
        });

    }
    private void initComponents(){
        calendarView=(CalendarView)this.findViewById(R.id.calendarView);
        btnAdd=(Button)findViewById(R.id.btn_add);
        btnSearch=(Button)findViewById(R.id.btn_search);
        textViewToShearch=(TextView)findViewById(R.id.editText);
        eventsFile=new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/events.xml");

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(MainActivity.this, CalendarAlarmReceiver.class);
        alarmIntent.putExtra(Constants.FILE_EXTRA,eventsFile);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        calendar=Calendar.getInstance();
        listViewEvents=new ListView(this);

        dataEvents=new DataEvents();
        dataEvents=Utils.readXML(eventsFile);
    }

    public void openAddEventIntent(Event event){
        Intent intentAdd=new Intent(this,AddActivity.class);
        intentAdd.putExtra(Constants.CALENDAR_EXTRA, calendar);
        if(event!=null){
            intentAdd.putExtra(Constants.ACTION_TYPE, Constants.ACTION_TYPE_EDIT);
            intentAdd.putExtra(Constants.EVENT_SELECTED, event);
        }else{
            intentAdd.putExtra(Constants.ACTION_TYPE, Constants.ACTION_TYPE_ADD);
        }
        startActivity(intentAdd);
    }

    public void performSearch(View view){
        listViewEvents=new ListView(this);
        String textToSearch=textViewToShearch.getText().toString();
        dataEvents=Utils.readXML(eventsFile);
        foundEvents=new ArrayList<>();
        for(Event event:dataEvents.getEvent()){
            if(event.getDenumire().contains(textToSearch)||event.getDateTime().toString().contains(textToSearch)){
                foundEvents.add(event);
            }
        }
        initAdapterAndItemClick();
        showDialogListView(view);
    }

    public void initAdapterAndItemClick(){
        ArrayAdapter<Event> adapter=new ArrayAdapter<Event>(this, R.layout.listview,R.id.txtitems, foundEvents);
        listViewEvents.setAdapter(adapter);
        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                event = (Event) parent.getItemAtPosition(position);
                Log.i("INFO*", "EVENT="+event.toString());
                openAddEventIntent(event);
            }
        });
    }

    public void performSearchByDate(View view, int year, int month, int day){
        listViewEvents=new ListView(this);
        dataEvents=Utils.readXML(eventsFile);
        foundEvents=new ArrayList<>();
        for(Event event:dataEvents.getEvent()){
            if(event.getDateTime().get(Calendar.YEAR)==year && event.getDateTime().get(Calendar.MONTH)==month
               && event.getDateTime().get(Calendar.DAY_OF_MONTH)==day){
                foundEvents.add(event);
            }
        }
        if(!foundEvents.isEmpty()) {
            initAdapterAndItemClick();
            showDialogListView(view);
        }
    }

    public void showDialogListView(View view){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        final AlertDialog alertd = builder.create();
        builder.setCancelable(false);
        builder.setView(listViewEvents);
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

    public void startAt() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Event firstEvent=dataEvents.getEvent().get(0);
        manager.set(AlarmManager.RTC_WAKEUP, firstEvent.getDateTime().getTimeInMillis(), pendingIntent);
    }
}