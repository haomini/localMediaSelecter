package com.example.localmediaselecter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class LocalUtils {
    public static void doSomething(Context context) {
        List<ModelLocalVideo> localVideoList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.e(cursor.getColumnName(i) + " : ", "" + cursor.getString(i));
        }

//        while(cursor.moveToNext()){
//            ModelLocalVideo localVideo = new ModelLocalVideo();
//            localVideo.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)));
//            localVideo.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA)));
//            localVideo.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION)));
//            localVideo.setFromFloder(cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.ALBUM)));
////            localVideo.setTimeTamp(cursor.getLong(cursor.getColumnIndex(Medi)));
//            localVideoList.add(localVideo);
//        }
    }
}
