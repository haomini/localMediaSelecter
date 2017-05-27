package com.example.zhiyicx.jpushtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.localmediaselecter.FragmentMediaSelect;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFragment();
    }

    private void initFragment() {
        FragmentMediaSelect mediaSelect = FragmentMediaSelect.getInstance(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home, mediaSelect)
                .commit();
    }
}
