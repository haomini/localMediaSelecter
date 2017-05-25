package com.example.localmediaselecter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25/025.
 */

public class AdapterMedia extends RecyclerView.Adapter<AdapterMedia.MediaViewHolder> {

    private List<ModelLocalVideo> videoList;
    private Context context;

    public AdapterMedia(Context context, List<ModelLocalVideo> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.haomini_item_video, null);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder mediaViewHolder, int i) {
        
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView preview;
        CheckBox status;

        public MediaViewHolder(View itemView) {
            super(itemView);
            preview = (ImageView) itemView.findViewById(R.id.item_preview);
            ViewGroup.LayoutParams layoutParams = preview.getLayoutParams();
            layoutParams.width = context.getResources().getDisplayMetrics().widthPixels / 3;
            layoutParams.height = context.getResources().getDisplayMetrics().widthPixels / 3;
            preview.setLayoutParams(layoutParams);
            status = (CheckBox) itemView.findViewById(R.id.item_preview_status);
        }
    }
}
