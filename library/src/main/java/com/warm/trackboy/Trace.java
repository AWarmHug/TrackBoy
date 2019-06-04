package com.warm.trackboy;

public class Trace {
    public static final String or="\\?";



    private String id;
    private String value;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Trace(String id, String value) {
        this.id = id;
        this.value = value;
    }

}
