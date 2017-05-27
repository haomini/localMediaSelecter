package com.example.localmediaselecter.utils;

import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2017/5/25/025.
 */

public class Constant {
    public static final String MODEL_KEY = "model_key";
    public static final int SINGLE_MODEL = 0;
    public static final int MUTI_MODEL = 1;

    public static final String MAX_NUM = "max_num";
    public static final int DEFAULT_NUM = 9;

    public static final String MEDIA_MODE = "media_mode";
    public static final int VIDEO_ONLY = 0;
    public static final int IMAGE_ONLY = 1;
    public static final int MUTI_MEDIA = 2;

    @IntDef({VIDEO_ONLY, IMAGE_ONLY, MUTI_MEDIA})
    public @interface MediaModel {
    }
}
