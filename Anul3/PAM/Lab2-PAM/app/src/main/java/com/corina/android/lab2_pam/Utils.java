package com.corina.android.lab2_pam;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by corina on 11/7/17.
 */
public class Utils {

    public static void writeXML(DataEvents dataEvents, File eventsFile){
        Serializer serializer = new Persister();
        try {
            serializer.write(dataEvents, eventsFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  DataEvents readXML(File eventsFile){
        Serializer serializer = new Persister();
        DataEvents dataEvents=null;
        try {
            dataEvents= serializer.read(DataEvents.class, eventsFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dataEvents==null) {
            dataEvents = new DataEvents();
            dataEvents.setEvent(new ArrayList<Event>());
        }
        return dataEvents;
    }
}
