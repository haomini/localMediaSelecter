package com.example.localmediaselecter;

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

import java.util.List;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class FragmentMediaSelect extends Fragment implements AdapterMedia.OnCountChangedListener {

    private static FragmentMediaSelect mInstance;
    private int modelKey;
    private int max_num;
    private List<ModelLocalVideo> videoList;
    private AdapterMedia adapterMedia;

    private ImageView back;
    private TextView ok;
    private RecyclerView mediaContainer;

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
        mediaContainer.setLayoutManager(new GridLayoutManager(getContext(), 3));

        videoList = LocalUtils.doSomething(getContext());
        adapterMedia = new AdapterMedia(getContext(), videoList);
        adapterMedia.setOnCountChangedListener(this);
        mediaContainer.setAdapter(adapterMedia);
        mediaContainer.addItemDecoration(new HaominiItemDecoration());
        return view;
    }

    @Override
    public void onCountChanged(List<ModelLocalVideo> videoList) {
        if (videoList.size() > 0) {
            ok.setEnabled(true);
            ok.setText("完成(" + videoList.size() + "/" + max_num);
        } else {
            ok.setEnabled(false);
            ok.setText("完成");
        }
    }
}
