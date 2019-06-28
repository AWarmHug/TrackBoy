package com.warm.ext

import com.warm.Utils

class TrackConfig {


    boolean enabled
    List<String> excludes = new ArrayList<>()


    boolean filter(String path) {
        boolean f = true;
        excludes.each {
            if (Utils.getFileName(path).contains(Utils.getFileName(it))) {
                f = false
            }
        }
        return f
    }

    @Override
    public String toString() {
        return "TrackConfig{" +
                "enabled=" + enabled +
                ", excludes=" + excludes +
                '}';
    }
}

