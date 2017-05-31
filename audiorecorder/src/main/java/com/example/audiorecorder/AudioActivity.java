package com.example.audiorecorder;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinksns.sociax.android.R;
import com.thinksns.sociax.t4.android.ThinksnsAbscractActivity;
import com.thinksns.sociax.t4.android.widget.MyChronometer;

public class AudioActivity extends ThinksnsAbscractActivity implements View.OnClickListener {

    private ImageView back;
    private TextView localAudio; // 选择本地音频
    private MyChronometer time; // 计时器
    private TextView redo, recorde, ok;// 重录, 录制, 完成

    private RecorderDealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreateNoTitle(savedInstanceState);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        dealer = new RecorderDealer();
    }

    private void initListener() {
        back.setOnClickListener(this);
        localAudio.setOnClickListener(this);
        redo.setOnClickListener(this);
        ok.setOnClickListener(this);

        //触摸录音
        recorde.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dealer.setStatus(RecorderDealer.START);
                        time.start();
                        break;
                    case MotionEvent.ACTION_UP:
                        dealer.setStatus(RecorderDealer.PAUSE);
                        time.stop();
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        this.ok = (TextView) findViewById(R.id.audio_ok);
        this.recorde = (TextView) findViewById(R.id.audio_do);
        this.redo = (TextView) findViewById(R.id.audio_redo);
        this.time = (MyChronometer) findViewById(R.id.audio_time);
        this.localAudio = (TextView) findViewById(R.id.select_local_audio);
        this.back = (ImageView) findViewById(R.id.audio_back);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_audio;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_back:
                finish();
                break;
            case R.id.select_local_audio:
                // TODO: 2017/5/31 :选择本地音频文件
                break;
            case R.id.audio_redo:
                //重录
                dealer.setStatus(RecorderDealer.REDO);
                break;
            case R.id.audio_ok:
                //完成
                dealer.setStatus(RecorderDealer.END);
                break;
        }
    }

    @Override
    @Deprecated
    public String getTitleCenter() {
        return null;
    }
}
