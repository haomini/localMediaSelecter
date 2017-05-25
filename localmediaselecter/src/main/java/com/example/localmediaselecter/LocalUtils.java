package com.example.localmediaselecter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class LocalUtils {
    public static List<ModelLocalVideo> doSomething(Context context) {
        List<ModelLocalVideo> localVideoList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            localVideoList.add(new ModelLocalVideo(cursor));
        }
        cursor.close();
        return localVideoList;
    }
}
