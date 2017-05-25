package com.example.localmediaselecter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhiyicx on 2017/5/25.
 */

public class FragmentMediaSelect extends Fragment {

    private ImageView back;
    private TextView ok;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.haomini_fragment_media, null);
        back = (ImageView) view.findViewById(R.id.haomini_title_back);
        ok = (TextView) view.findViewById(R.id.haomini_title_right);
        LocalUtils.doSomething(getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
