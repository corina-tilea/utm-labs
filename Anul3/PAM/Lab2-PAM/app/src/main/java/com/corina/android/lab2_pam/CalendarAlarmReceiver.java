package com.corina.android.lab2_pam;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class CalendarAlarmReceiver extends BroadcastReceiver {
    private DataEvents dataEvents;
    private File eventsFile;

    public CalendarAlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //dataEvents INITIALIZATION
        Bundle dataFromIntent=intent.getExtras();
        eventsFile=(File)dataFromIntent.get(Constants.FILE_EXTRA);
        dataEvents=Utils.readXML(eventsFile);
        Event eventToShow=dataEvents.getEvent().get(0);
        dataEvents.getEvent().remove(0);
        Utils.writeXML(dataEvents, eventsFile);

        //RESTART Alarm, set The next Upcomming event
        Event eventToProgramNext=dataEvents.getEvent().get(0);
        Intent alarmIntent = new Intent(context, CalendarAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, eventToProgramNext.getDateTime().getTimeInMillis(), pendingIntent);

        //Start Activity that SHOWS Event
        Intent intenetShowAlarm=new Intent(context, AlarmView.class);
        intenetShowAlarm.putExtra(Constants.EVENT_FOR_SHOW_ALARM, eventToShow);
        intenetShowAlarm.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intenetShowAlarm);

    }
}
