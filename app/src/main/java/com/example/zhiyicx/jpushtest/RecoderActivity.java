package com.example.zhiyicx.jpushtest;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.io.File;
import java.io.IOException;

public class RecoderActivity extends AppCompatActivity {

    private android.widget.Chronometer chronometer;
    private android.widget.Button start, end;

    private MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoder);
        this.start = (Button) findViewById(R.id.start);
        this.end = (Button) findViewById(R.id.end);
        this.chronometer = (Chronometer) findViewById(R.id.chronometer);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.start();
                try {
                    String path = Environment.getExternalStorageDirectory() + "/shangxue/audio_cache";
                    if (!new File(path).exists()) {
                        new File(path).mkdirs();
                    }
                    String absPath = path + "/cache_" + System.currentTimeMillis() + ".3gp";
                    if (!new File(absPath).exists()) {
                        new File(absPath).createNewFile();
                    }
                    doSomething(absPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.stop();
            }
        });
    }

    public void doSomething(String path) throws IOException {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(path);
        recorder.prepare();
        recorder.start();
    }


}
