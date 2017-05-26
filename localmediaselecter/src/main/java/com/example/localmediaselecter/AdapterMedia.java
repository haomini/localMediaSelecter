package com.example.localmediaselecter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25/025.
 */

public class AdapterMedia extends RecyclerView.Adapter<AdapterMedia.MediaViewHolder> {

    private List<ModelLocalVideo> videoList;
    private Context context;
    private DealFactory factory;
    private List<ModelLocalVideo> countList;
    private OnCountChangedListener onCountChangedListener;

    public AdapterMedia(Context context, List<ModelLocalVideo> videoList) {
        this.context = context;
        this.videoList = videoList;
        countList = new LinkedList<>();
        factory = new DealFactory(context);
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.haomini_item_video, viewGroup, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MediaViewHolder mediaViewHolder, int i) {
        factory.setBitmap(mediaViewHolder.preview, videoList.get(i).getId());
        setStatus(mediaViewHolder, videoList.get(i).isChecked());
        mediaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mediaViewHolder.getAdapterPosition();
                videoList.get(position).setChecked(!videoList.get(position).isChecked());
                setStatus(mediaViewHolder, videoList.get(position).isChecked());
                if (videoList.get(position).isChecked()) {
                    countList.add(videoList.get(position));
                } else {
                    countList.remove(videoList.get(position));
                }
                //计数
                if (onCountChangedListener != null) {
                    onCountChangedListener.onCountChanged(countList);
                }
            }
        });
    }

    public void setStatus(MediaViewHolder holder, boolean status) {
        holder.status.setChecked(status);
        holder.preview.setAlpha(status ? 0.3f : 1.0f);
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

    public void setOnCountChangedListener(OnCountChangedListener listener) {
        this.onCountChangedListener = listener;
    }

    public interface OnCountChangedListener {
        void onCountChanged(List<ModelLocalVideo> videoList);
    }
}
