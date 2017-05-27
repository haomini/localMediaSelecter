package com.example.localmediaselecter.model;

import android.database.Cursor;
import android.os.Parcel;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * Created by zhiyicx on 2017/5/27.
 */

public class ModelLocalImage extends ModelLocalMedia {

    //图片容器
    private String bucketDisplayName;
    //图片高
    private int width;
    //图片宽
    private int height;

    public ModelLocalImage(@NonNull Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)));
        this.setData(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
        this.setDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME)));
        this.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.SIZE)));
        this.setMimeType(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.MIME_TYPE)));
        this.setDateAdded(cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_ADDED)));
        this.setDataModified(cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_MODIFIED)));
        this.setBucketDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME)));
        this.setWidth(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH)));
        this.setHeight(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT)));
    }

    public static final Creator<ModelLocalImage> CREATOR = new Creator<ModelLocalImage>() {
        @Override
        public ModelLocalImage createFromParcel(Parcel in) {
            return new ModelLocalImage(in);
        }

        @Override
        public ModelLocalImage[] newArray(int size) {
            return new ModelLocalImage[size];
        }
    };

    public ModelLocalImage(Parcel in) {
        super(in);
        bucketDisplayName = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(bucketDisplayName);
        dest.writeInt(width);
        dest.writeInt(height);
    }

    public String getBucketDisplayName() {
        return bucketDisplayName;
    }

    public void setBucketDisplayName(String bucketDisplayName) {
        this.bucketDisplayName = bucketDisplayName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
