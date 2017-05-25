package com.example.localmediaselecter;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class ModelLocalVideo {
    private String path;
    private String fromFloder;
    private long duration;
    private long timeTamp;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFromFloder() {
        return fromFloder;
    }

    public void setFromFloder(String fromFloder) {
        this.fromFloder = fromFloder;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getTimeTamp() {
        return timeTamp;
    }

    public void setTimeTamp(long timeTamp) {
        this.timeTamp = timeTamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
