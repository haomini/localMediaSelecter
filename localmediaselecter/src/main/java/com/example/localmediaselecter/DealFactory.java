package com.example.localmediaselecter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhiyicx on 2017/5/26.
 */

public class DealFactory {
    private ExecutorService service;
    private LruCache<String, Bitmap> lruCache;
    private LoadThumbUtils utils;

    public DealFactory(Context context) {
        //cpu核心数
        int processors = Runtime.getRuntime().availableProcessors();
        service = new ThreadPoolExecutor(processors, processors,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        lruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        utils = new LoadThumbUtils(context, Constant.VIDEO_ONLY);
    }

    public void setBitmap(ImageView imageView, int id) {
        imageView.setTag(id);
        Bitmap bitmap = lruCache.get(String.valueOf(id));
        if (bitmap == null) {
            service.submit(new LoadRunnable(id, imageView));
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public class LoadRunnable implements Runnable {

        private int id;
        private ImageView imageView;

        public LoadRunnable(int id, ImageView imageView) {
            this.id = id;
            this.imageView = imageView;
        }

        @Override
        public void run() {
            final Bitmap bitmap = utils.getThumbByLocal(id);
            if (bitmap == null) {
                lruCache.put(String.valueOf(id), bitmap);
            } else {
                lruCache.put(String.valueOf(id), bitmap);
                if(id == (int)imageView.getTag())
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }
    }
}
