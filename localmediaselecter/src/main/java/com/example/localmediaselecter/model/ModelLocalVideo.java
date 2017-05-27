package com.example.localmediaselecter.model;

import android.database.Cursor;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * Created by zhouhao on 2017/5/25.
 */

public class ModelLocalVideo extends ModelLocalMedia {

    //视频时长 unit:ms
    private long duration;

    protected ModelLocalVideo(Parcel in) {
        super(in);
        duration = in.readLong();
    }

    public static final Creator<ModelLocalVideo> CREATOR = new Creator<ModelLocalVideo>() {
        @Override
        public ModelLocalVideo createFromParcel(Parcel in) {
            return new ModelLocalVideo(in);
        }

        @Override
        public ModelLocalVideo[] newArray(int size) {
            return new ModelLocalVideo[size];
        }
    };

    public ModelLocalVideo(@NonNull Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setData(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA)));
        this.setDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)));
        this.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE)));
        this.setMimeType(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.MIME_TYPE)));
        this.setDateAdded(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_ADDED)));
        this.setDataModified(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_MODIFIED)));
        this.setBucketDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME)));
        this.setWidth(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.VideoColumns.WIDTH)));
        this.setHeight(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.VideoColumns.HEIGHT)));
        this.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION)));
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(duration);
    }
}
