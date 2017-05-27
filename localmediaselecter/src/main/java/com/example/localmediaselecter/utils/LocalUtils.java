package com.example.localmediaselecter.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.localmediaselecter.model.ModelLocalImage;
import com.example.localmediaselecter.model.ModelLocalMedia;
import com.example.localmediaselecter.model.ModelLocalVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class LocalUtils {
    private static List<ModelLocalMedia> getLocalVideo(Context context) {
        List<ModelLocalMedia> localVideoList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            localVideoList.add(new ModelLocalVideo(cursor));
        }
        cursor.close();
        return localVideoList;
    }

    private static List<ModelLocalMedia> getLocalPic(Context context) {
        List<ModelLocalMedia> localPic = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            localPic.add(new ModelLocalImage(cursor));
        }
        cursor.close();
        return localPic;
    }

    public static List<ModelLocalMedia> getLocalMedia(Context context, @Constant.MediaModel int model_key) {
        List<ModelLocalMedia> mediaList = null;
        switch (model_key) {
            case Constant.IMAGE_ONLY:
                mediaList = getLocalPic(context);
                break;
            case Constant.VIDEO_ONLY:
                mediaList = getLocalVideo(context);
                break;
            case Constant.MUTI_MEDIA:
                //待处理
                break;
        }
        return mediaList;
    }
}
