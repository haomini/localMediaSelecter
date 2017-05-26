package com.example.localmediaselecter;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class ModelLocalVideo {
    //视频id
    private int id;
    //视频path
    private String data;
    //视频时长 unit:ms
    private long duration;
    //视频名字
    private String displayName;
    //视频大小
    private long size;
    //视频格式
    private String mimeType;
    //视频添加时间
    private long dateAdded;
    //视频最后修改时间
    private long dataModified;
    //视频缩略图
    private String videoThumb;

    public ModelLocalVideo(@NonNull Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setData(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setMimeType(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setDateAdded(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setDataModified(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDataModified() {
        return dataModified;
    }

    public void setDataModified(long dataModified) {
        this.dataModified = dataModified;
    }
}
