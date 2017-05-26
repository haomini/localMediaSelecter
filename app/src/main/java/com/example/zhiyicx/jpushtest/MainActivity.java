package com.example.zhiyicx.jpushtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.localmediaselecter.Constant;
import com.example.localmediaselecter.FragmentMediaSelect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.MODEL_KEY, Constant.SINGLE_MODEL);
        FragmentMediaSelect mediaSelect = FragmentMediaSelect.getInstance(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home, mediaSelect)
                .commit();
    }
}
