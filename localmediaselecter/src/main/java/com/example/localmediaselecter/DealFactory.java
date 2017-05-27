package com.example.localmediaselecter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.SparseArray;
import android.widget.ImageView;

/**
 * Created by zhiyicx on 2017/5/26.
 */

public class DealFactory {
    private SparseArray sparseArray;
    private LruCache<String, Bitmap> lruCache;
    private LoadThumbUtils utils;

    public DealFactory(Context context, @Constant.MediaModel int model) {
        sparseArray = new SparseArray<LoadTask>();

        lruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        utils = new LoadThumbUtils(context, model);
    }

    public void setBitmap(ImageView imageView, int id) {
        imageView.setImageResource(R.drawable.image80x80yuanjiao);
        imageView.setTag(id);
        Bitmap bitmap = lruCache.get(String.valueOf(id));
        if (bitmap == null) {
            LoadTask loadTask = new LoadTask(id, imageView);
            sparseArray.put(id, loadTask);
            loadTask.execute();

        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void pauseTask() {
        for (int i = 0; i < sparseArray.size(); i++) {
            LoadTask loadTask = ((LoadTask) sparseArray.get(i));
            if (loadTask != null && !loadTask.isCancelled()) {
                loadTask.cancel(false);
            }
        }
    }


    @Deprecated
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
                return;
                //lruCache.put(String.valueOf(id), bitmap);
            } else {
                lruCache.put(String.valueOf(id), bitmap);
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }
    }

    public class LoadTask extends AsyncTask<Integer, Void, Bitmap> {
        private int id;
        private ImageView imageView;

        public LoadTask(int id, ImageView imageView) {
            this.id = id;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {
            return utils.getThumbByLocal(id);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                return;
                //lruCache.put(String.valueOf(id), bitmap);
            } else {
                lruCache.put(String.valueOf(id), bitmap);
                if (id == (int) imageView.getTag())
                    imageView.setImageBitmap(bitmap);
            }
        }
    }

}
