package com.example.localmediaselecter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.localmediaselecter.model.ModelLocalVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class LocalUtils {
    public static List<ModelLocalVideo> getLocalVideo(Context context) {
        List<ModelLocalVideo> localVideoList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            localVideoList.add(new ModelLocalVideo(cursor));
        }
        cursor.close();
        return localVideoList;
    }

    public static List getLocalPic(Context context) {
        List localPic = new ArrayList();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.e(cursor.getColumnName(i) + " 0:", cursor.getString(i)+" ;");
        }
//        while(cursor.moveToNext()){
//            localPic.add()
//        }
        return null;
    }
}
