package com.corina.android.lab2_pam;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Collections;
import java.util.List;

/**
 * Created by corina on 10/30/17.
 */
@Root
public class DataEvents {
    @ElementList
    private List<Event> event;

    public DataEvents() {
    }

    public DataEvents(List<Event> event) {
        this.event = event;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public void sortEvents(){
        Collections.sort(event);
    }
    public void updateEventFromList(Event eventToUpdate){

        for(Event eventFromList:event){
            if(eventFromList.getId()==eventToUpdate.getId()){
                eventFromList.setDenumire(eventToUpdate.getDenumire());
                eventFromList.setDateTime(eventToUpdate.getDateTime());
            }

        }
    }
}
