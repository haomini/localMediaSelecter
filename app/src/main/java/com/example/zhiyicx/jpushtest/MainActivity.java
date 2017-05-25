package com.example.zhiyicx.jpushtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.localmediaselecter.FragmentMediaSelect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentMediaSelect fragment = new FragmentMediaSelect();
        getSupportFragmentManager().beginTransaction().add(R.id.home, fragment).commit();
    }
}
