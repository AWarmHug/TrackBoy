package com.warm.someaop;

public class Trace {
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
