package com.warm.track;

public class Trace {
    public static final String or = "#";

    public static final String r = "^^r^$";


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
