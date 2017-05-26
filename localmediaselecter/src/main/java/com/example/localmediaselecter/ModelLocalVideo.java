package com.example.localmediaselecter;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class ModelLocalVideo implements Parcelable {
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
    //是否被选中
    private boolean isChecked;

    protected ModelLocalVideo(Parcel in) {
        id = in.readInt();
        data = in.readString();
        duration = in.readLong();
        displayName = in.readString();
        size = in.readLong();
        mimeType = in.readString();
        dateAdded = in.readLong();
        dataModified = in.readLong();
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ModelLocalVideo(@NonNull Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID)));
        this.setData(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA)));
        this.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION)));
        this.setDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)));
        this.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE)));
        this.setMimeType(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.MIME_TYPE)));
        this.setDateAdded(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_ADDED)));
        this.setDataModified(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_MODIFIED)));
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(data);
        dest.writeLong(duration);
        dest.writeString(displayName);
        dest.writeLong(size);
        dest.writeString(mimeType);
        dest.writeLong(dateAdded);
        dest.writeLong(dataModified);
    }

    @Override
    public String toString() {
        return "ModelLocalVideo{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", duration=" + duration +
                ", displayName='" + displayName + '\'' +
                ", size=" + size +
                ", mimeType='" + mimeType + '\'' +
                ", dateAdded=" + dateAdded +
                ", dataModified=" + dataModified +
                ", isChecked=" + isChecked +
                '}';
    }
}
