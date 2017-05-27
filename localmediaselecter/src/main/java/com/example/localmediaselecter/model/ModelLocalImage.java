package com.example.localmediaselecter.model;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

/**
 * Created by zhiyicx on 2017/5/27.
 */

public class ModelLocalImage extends ModelLocalMedia {
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
}
