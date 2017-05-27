package com.example.zhiyicx.jpushtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.localmediaselecter.model.ModelLocalVideo;
import com.example.localmediaselecter.utils.Constant;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(Constant.MODEL_KEY, Constant.MUTI_MODEL);
                intent.putExtra(Constant.MEDIA_MODE, Constant.VIDEO_ONLY);
                intent.putExtra(Constant.MAX_NUM, Constant.DEFAULT_NUM);
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(Constant.MODEL_KEY, Constant.MUTI_MODEL);
                intent.putExtra(Constant.MEDIA_MODE, Constant.IMAGE_ONLY);
                intent.putExtra(Constant.MAX_NUM, Constant.DEFAULT_NUM);
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.audio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra(Constant.MODEL_KEY, Constant.MUTI_MODEL);
                intent.putExtra(Constant.MEDIA_MODE, Constant.AUDIO_ONLY);
                intent.putExtra(Constant.MAX_NUM, Constant.DEFAULT_NUM);
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.e("M", "o" + ((List<ModelLocalVideo>)data.getSerializableExtra("data")).get(0).getDuration());
        }
    }
}
