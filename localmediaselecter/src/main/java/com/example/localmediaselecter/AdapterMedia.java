package com.example.localmediaselecter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.localmediaselecter.model.ModelLocalMedia;
import com.example.localmediaselecter.utils.Constant;
import com.example.localmediaselecter.utils.DealFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25/025.
 */

public class AdapterMedia extends RecyclerView.Adapter<AdapterMedia.MediaViewHolder> {

    private List<? extends ModelLocalMedia> mediaList;
    private Context context;
    private DealFactory factory;
    private List<ModelLocalMedia> countList;
    private OnCountChangedListener onCountChangedListener;

    public AdapterMedia(Context context, List<? extends ModelLocalMedia> mediaList, @Constant.MediaModel int model) {
        this.context = context;
        this.mediaList = mediaList;
        countList = new LinkedList<>();
        factory = new DealFactory(context, model);
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.haomini_item_video, viewGroup, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MediaViewHolder mediaViewHolder, int i) {
        factory.setBitmap(mediaViewHolder.preview, mediaList.get(i).getId());
        setStatus(mediaViewHolder, mediaList.get(i).isChecked());
        mediaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mediaViewHolder.getAdapterPosition();
                mediaList.get(position).setChecked(!mediaList.get(position).isChecked());
                setStatus(mediaViewHolder, mediaList.get(position).isChecked());
                if (mediaList.get(position).isChecked()) {
                    countList.add(mediaList.get(position));
                } else {
                    countList.remove(mediaList.get(position));
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
        return mediaList.size();
    }


    public class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView preview;
        CheckBox status;
        TextView itemSize, itemName;
        ImageView itemPreview;

        public MediaViewHolder(View itemView) {
            super(itemView);
            preview = (ImageView) itemView.findViewById(R.id.item_preview);
            if (preview != null) {
                ViewGroup.LayoutParams layoutParams = preview.getLayoutParams();
                layoutParams.width = context.getResources().getDisplayMetrics().widthPixels / 3;
                layoutParams.height = context.getResources().getDisplayMetrics().widthPixels / 3;
                preview.setLayoutParams(layoutParams);
            }
            status = (CheckBox) itemView.findViewById(R.id.item_preview_status);
            itemSize = (TextView) itemView.findViewById(R.id.item_size);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemPreview = (ImageView) itemView.findViewById(R.id.item_preview_audio);
        }
    }

    public void setOnCountChangedListener(OnCountChangedListener listener) {
        this.onCountChangedListener = listener;
    }

    public interface OnCountChangedListener<T extends ModelLocalMedia> {
        void onCountChanged(List<T> mediaList);
    }
}
