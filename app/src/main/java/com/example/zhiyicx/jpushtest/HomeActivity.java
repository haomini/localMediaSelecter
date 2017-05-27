package com.example.zhiyicx.jpushtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.localmediaselecter.Constant;
import com.example.localmediaselecter.FragmentMediaSelect;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFragment();
    }

    private void initFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.MODEL_KEY, Constant.MUTI_MODEL);
        bundle.putInt(Constant.MEDIA_MODE, Constant.IMAGE_ONLY);
        bundle.putInt(Constant.MAX_NUM, Constant.DEFAULT_NUM);
        FragmentMediaSelect mediaSelect = FragmentMediaSelect.getInstance(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home, mediaSelect)
                .commit();
    }
}
