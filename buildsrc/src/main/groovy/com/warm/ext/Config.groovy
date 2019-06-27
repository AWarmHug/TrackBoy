package com.warm.ext

class Config {


    boolean enabled;
    String[] exclude;



    @Override
    public String toString() {
        return "TrackExtension{" +
                "enabled=" + enabled +
                ", exclude=" + Arrays.toString(exclude) +
                '}';
    }
}

