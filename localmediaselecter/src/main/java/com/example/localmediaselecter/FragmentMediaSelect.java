package com.example.localmediaselecter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.localmediaselecter.model.ModelLocalMedia;
import com.example.localmediaselecter.utils.Constant;
import com.example.localmediaselecter.utils.LocalUtils;
import com.example.localmediaselecter.widget.recycler.HaominiItemDecoration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class FragmentMediaSelect extends Fragment implements AdapterMedia.OnCountChangedListener, View.OnClickListener {

    private static FragmentMediaSelect mInstance;
    private ImageView back;
    private TextView ok;
    private RecyclerView mediaContainer;

    private int modelKey;
    private int max_num;

    @Constant.MediaModel
    private int mediaMode;

    private List<ModelLocalMedia> mediaList;
    private List<ModelLocalMedia> resultList;
    private AdapterMedia adapterMedia;

    public static FragmentMediaSelect getInstance(Bundle bundle) {
        if (mInstance == null)
            mInstance = new FragmentMediaSelect();
        mInstance.setArguments(bundle);
        return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelKey = getArguments().getInt(Constant.MODEL_KEY, Constant.SINGLE_MODEL);
        mediaMode = getArguments().getInt(Constant.MEDIA_MODE, Constant.VIDEO_ONLY);
        if (modelKey == Constant.MUTI_MODEL) {
            max_num = getArguments().getInt(Constant.MAX_NUM, Constant.DEFAULT_NUM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return findViews();
    }

    public View findViews() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.haomini_fragment_media, null);
        back = (ImageView) view.findViewById(R.id.haomini_title_back);
        ok = (TextView) view.findViewById(R.id.haomini_title_right);
        mediaContainer = (RecyclerView) view.findViewById(R.id.haomini_media_container);

        manageAdapter();
        initListener();
        return view;
    }

    /**
     * adapter 相关
     */
    public void manageAdapter() {
        //数据源
        mediaList = LocalUtils.getLocalMedia(getContext(), mediaMode);
        adapterMedia = new AdapterMedia(getContext(), mediaList, mediaMode);
        mediaContainer.setAdapter(adapterMedia);
        //grid 布局
        mediaContainer.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //间距
        mediaContainer.addItemDecoration(new HaominiItemDecoration());
    }

    public void initListener() {
        ok.setOnClickListener(this);
        //选中数量变化监听
        adapterMedia.setOnCountChangedListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onCountChanged(List videoList) {
        resultList = videoList;
        if (videoList.size() > 0) {
            ok.setEnabled(true);
            ok.setText("完成(" + videoList.size() + "/" + max_num);
        } else {
            ok.setEnabled(false);
            ok.setText("完成");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ok) { //完成事件
            Intent intent = new Intent();
            intent.putExtra("data", (LinkedList) resultList);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        } else if (v == back) {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        }
    }
}
