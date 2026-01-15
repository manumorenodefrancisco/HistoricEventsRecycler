package com.manumorenodefrancisco.historiceventsrecycler;

public class EventModel {
    public String eventName;
    public String eventDate;
    public String eventLocation;
    public int fallos= 0;
    public EventModel(String eventName, String eventDate, String eventLocation) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    public int getFallos() {
        return fallos;
    }

    public void incrementarFallos() {
        fallos++;
    }
    public String getEventName() {
        return eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }
}


