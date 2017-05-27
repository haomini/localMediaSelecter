package com.example.localmediaselecter.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;

/**
 * Created by zhiyicx on 2017/5/26.
 */

public class LoadThumbUtils {
    private int model;
    private Context context;

    public LoadThumbUtils(Context context, @Constant.MediaModel int model) {
        this.context = context;
        this.model = model;
    }

    public Bitmap getThumbByLocal(int media_id) {
        Bitmap bitmap = null;
        switch (model) {
            case Constant.VIDEO_ONLY:
                bitmap = MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(), media_id,
                        MediaStore.Video.Thumbnails.MINI_KIND, null);
                break;
            case Constant.IMAGE_ONLY:
                bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), media_id,
                        MediaStore.Video.Thumbnails.MINI_KIND, null);
                break;
            case Constant.AUDIO_ONLY: //音频无bitmap
                break;
        }
        return bitmap;
    }

}
