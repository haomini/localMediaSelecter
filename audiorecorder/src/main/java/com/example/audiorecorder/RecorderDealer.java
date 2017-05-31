package com.example.audiorecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.IntDef;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 音频录制 _haomini 2017/5/31.
 */

public class RecorderDealer {

    /*
     * 开始
     * 暂停
     * 结束
     * 重录
     */
    public static final int START = 100;
    public static final int PAUSE = 101;
    public static final int END = 103;
    public static final int REDO = 104;

    @IntDef({START, PAUSE, END, REDO})
    public @interface STATUS {
    }

    /**
     * 默认最长录制时间
     */
    private static final int MAX_TIME = 30;

    /**
     * temp路径 : 带合成的amr文件保存路径
     */
    private static final String DEFAULT_TEMP = "shangxue/audio_temp";

    /**
     * cache路径 : 合成过后的amr文件保存路径
     */
    private static final String CACHE_PATH = "shangxue/audio_cache";

    /*
     * 现在处于第几个temp.amr
     */
    private int count = -1;

    /*
     * 已经合成的amr文件的文件名格式化
     */
    private SimpleDateFormat formate;

    /*
     * 所有temp.amr文件
     */
    private List<File> listFiles;

    /**
     * 标记 : 确定是否已经start()了
     */
    private boolean isStart;

    private MediaRecorder recorder;

    public RecorderDealer() {
        recorder = new MediaRecorder();
        formate = new SimpleDateFormat("yyyyMMdd-HHmm");
        listFiles = new ArrayList<>();
    }

    //让MediaRecorder从initial状态进入prepared状态必须的步骤
    private void initialToPrepared() {

        //1. initial to initialed
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        //2. initialed to DataSourceConfigured
        //格式设置为amr
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        String path = Environment.getExternalStorageDirectory() + File.separator + DEFAULT_TEMP
                + File.separator;
        File gp3 = new File(path + getTempName());
        if (!gp3.getParentFile().exists()) {
            gp3.getParentFile().mkdirs();
        }

        try {

            //添加到listFiles
            listFiles.add(gp3);

            recorder.setOutputFile(gp3.getPath());

            // 3. DataSourceConfigured to Prepared
            recorder.prepare();
        } catch (IOException e) {
            listFiles.remove(gp3);
            e.printStackTrace();
        }
    }

    /**
     * 设置相应状态, 就可以了
     *
     * @param status
     */
    public void setStatus(@STATUS int status) {
        switch (status) {
            case START://开始录音或者继续录音
                isStart = false;
                initialToPrepared();
                recorder.start();
                isStart = true;
                break;

            case PAUSE://暂停录音
                if (isStart) {
                    recorder.stop();
                }
                break;

            case END://结束录音,合并amr文件
                try {
                    gather();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recorder.release();
                break;

            case REDO://重录,删除amr文件
                recorder.reset();
                deleteAll();
                break;
        }
    }

    /**
     * 合并音频文件
     *
     * @throws IOException
     */
    public void gather() throws IOException {
        //重置temp记录
        count = -1;

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + CACHE_PATH
                + File.separator + getCacheName());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < listFiles.size(); i++) {
            FileInputStream fileInputStream = new FileInputStream(listFiles.get(i));
            byte[] myByte = new byte[fileInputStream.available()];
            //文件长度
            int length = myByte.length;

            //头文件
            if (i == 0) {
                while (fileInputStream.read(myByte) != -1) {
                    fileOutputStream.write(myByte, 0, length);
                }
            }

            //之后的文件，去掉头文件(6字节标记)就可以了
            else {
                while (fileInputStream.read(myByte) != -1) {
                    fileOutputStream.write(myByte, 6, length - 6);
                }
            }
            fileOutputStream.flush();
            fileInputStream.close();
        }
        fileOutputStream.close();
        deleteAll();
    }

    /**
     * 删除音频temp文件文件夹的所有临时temp.amr
     */
    public void deleteAll() {
        //重置temp记录
        count = -1;
        File tempFile = new File(Environment.getExternalStorageDirectory() + File.separator
                + DEFAULT_TEMP);
        if (tempFile.exists() && tempFile.isDirectory()) {
            for (File file : tempFile.listFiles()) {
                file.delete();
            }
            listFiles.clear();
        }
    }

    /**
     * 获取临时文件名(待删除)
     *
     * @return
     */
    private String getTempName() {
        count++;
        return "temp" + count + ".amr";
    }

    /**
     * 获取缓存文件名(保存)
     *
     * @return
     */
    private String getCacheName() {
        return formate.format(System.currentTimeMillis()) + ".amr";
    }
}
