package com.warm.someaop;

public class Trace {
    private String eventId;
    private String value;

    public String getEventId() {
        return eventId;
    }

    public String getValue() {
        return value;
    }

    public Trace(String eventId, String value) {
        this.eventId = eventId;
        this.value = value;
    }

}
