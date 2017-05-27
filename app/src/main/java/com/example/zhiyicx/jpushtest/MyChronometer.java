package com.example.zhiyicx.jpushtest;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

/**
 * Created by zhiyicx on 2017/5/27.
 */

public class MyChronometer extends Chronometer implements Chronometer.OnChronometerTickListener {

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnChronometerTickListener(this);
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        long time = SystemClock.elapsedRealtime() - chronometer.getBase();
        if (time > 3600000L) {
            chronometer.setFormat("0%s");
        } else {
            chronometer.setFormat("00:%s");
        }
    }
}
