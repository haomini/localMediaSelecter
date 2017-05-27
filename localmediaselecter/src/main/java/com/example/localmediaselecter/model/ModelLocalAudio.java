package com.example.localmediaselecter.model;

import android.database.Cursor;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * Created by zhiyicx on 2017/5/27.
 */

public class ModelLocalAudio extends ModelLocalMedia {
    //音频时长 unit:ms
    private long duration;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(duration);
    }

    public static final Creator<ModelLocalAudio> CREATOR = new Creator<ModelLocalAudio>() {
        @Override
        public ModelLocalAudio createFromParcel(Parcel in) {
            return new ModelLocalAudio(in);
        }

        @Override
        public ModelLocalAudio[] newArray(int size) {
            return new ModelLocalAudio[size];
        }
    };

    public ModelLocalAudio(Parcel in) {
        super(in);
        duration = in.readLong();
    }

    public ModelLocalAudio(@NonNull Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID)));
        this.setData(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA)));
        this.setDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME)));
        this.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE)));
        this.setMimeType(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.MIME_TYPE)));
        this.setDateAdded(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATE_ADDED)));
        this.setDataModified(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATE_MODIFIED)));
        this.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION)));
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
