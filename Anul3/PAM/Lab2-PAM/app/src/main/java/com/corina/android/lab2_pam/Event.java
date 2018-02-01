package com.corina.android.lab2_pam;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by corina on 10/29/17.
 */

@Root
public class Event implements Comparable<Event>, Serializable{
    @Element
    private int id;
    @Element
    private String denumire;
    @Element
    private Calendar dateTime;
    @Element
    private boolean announced=false;

    public Event() {
    }

    public Event(int id, String denumire, Calendar dateTime, boolean announced) {
        this.denumire = denumire;
        this.dateTime = dateTime;
        this.id = id;
        this.announced = announced;
    }

    public boolean isAnnounced() {
        return announced;
    }

    public void setAnnounced(boolean announced) {
        this.announced = announced;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (announced != event.announced) return false;
        if (denumire != null ? !denumire.equals(event.denumire) : event.denumire != null)
            return false;
        return dateTime != null ? dateTime.equals(event.dateTime) : event.dateTime == null;

    }
    public String getDateAsStringFormatted(){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatDate.format(getDateTime().getTime());
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (denumire != null ? denumire.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (announced ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return denumire+" - "+getDateAsStringFormatted();
    }

    @Override
    public int compareTo(Event o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
