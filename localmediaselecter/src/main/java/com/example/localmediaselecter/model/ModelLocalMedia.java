package com.example.localmediaselecter.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haomini on 2017/5/27.
 */

public class ModelLocalMedia implements Parcelable {
    //media_id
    private int id;
    //media_path
    private String data;
    //media名字
    private String displayName;
    //media大小
    private long size;
    //media格式
    private String mimeType;
    //media添加时间
    private long dateAdded;
    //media最后修改时间
    private long dataModified;
    //是否被选中
    private boolean isChecked;

    public ModelLocalMedia() {

    }

    protected ModelLocalMedia(Parcel in) {
        id = in.readInt();
        data = in.readString();
        displayName = in.readString();
        size = in.readLong();
        mimeType = in.readString();
        dateAdded = in.readLong();
        dataModified = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(data);
        dest.writeString(displayName);
        dest.writeLong(size);
        dest.writeString(mimeType);
        dest.writeLong(dateAdded);
        dest.writeLong(dataModified);
    }

    public static final Creator<ModelLocalMedia> CREATOR = new Creator<ModelLocalMedia>() {
        @Override
        public ModelLocalMedia createFromParcel(Parcel in) {
            return new ModelLocalMedia(in);
        }

        @Override
        public ModelLocalMedia[] newArray(int size) {
            return new ModelLocalMedia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
