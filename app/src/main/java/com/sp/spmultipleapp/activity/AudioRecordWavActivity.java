package com.sp.spmultipleapp.activity;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sp.spmultipleapp.R;
import com.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 音频模块调试。
 */
public class AudioRecordWavActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AudioRecordWavActivity";
    private Button mRecordStartStop;
    private boolean mRecording = false;
    private Handler mHandler = new Handler();

    private static final int RECORDER_BPP = 16;///PCM 16 bit per sample
    private static final int RECORDER_SAMPLERATE = 16000; //8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static final int MAX_RECORDING_TIME = 30000;

    private int mRecBufferSize = 0;
    private AudioRecord mAudioRecorder = null;
    private Thread mAudioRecordingThread = null;

    private String mAudioFilePath_Temp;
    private String mRecordFilePath;
    private String mAudioFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record_wav);
        mRecordStartStop = (Button) findViewById(R.id.btn_start_stop_record);
        mRecordStartStop.setOnClickListener(this);

        updateRecordingButton();

        initFile();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRecording) {
            stopRecording();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initFile() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        String baseFileName = "Rec_" + formatter.format(new Date());
        String dirPath = Environment.getExternalStorageDirectory() +
                File.separator + "audioRec";
        LogUtil.d(TAG, "initFile()>>dirPath=" + dirPath);
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        mAudioFilePath_Temp = dirPath + File.separator + baseFileName + ".raw";
        mRecordFilePath = dirPath + File.separator + baseFileName + ".wav";
    }

    @Override
    public void onClick(View v) {
        if (v == mRecordStartStop) {
            if (mRecording) {
                stopRecording();
            } else {
                mHandler.postDelayed(mDelayStartRecording, 500);
            }
        }

    }



    private Runnable mDelayStartRecording = new Runnable() {
        public void run() {
            startRecording();
        }
    };
    private Runnable mTimeoutRecordRunnble = new Runnable() {
        @Override
        public void run() {
            stopRecording();
        }
    };
    private void updateRecordingButton() {
        LogUtil.d(TAG, "updateRecordingButton()>>mRecording:" + mRecording);
        if (mRecording){
            mRecordStartStop.setText("stop record");
        } else {
            mRecordStartStop.setText("record");
        }
    }
    private void startRecording() {
        LogUtil.d(TAG, "startRecording()");
        try {

            mRecBufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);

            mAudioRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                    RECORDER_SAMPLERATE,
                    RECORDER_CHANNELS,
                    RECORDER_AUDIO_ENCODING,
                    mRecBufferSize);

            mAudioRecorder.startRecording();
            mRecording = true;

            mAudioRecordingThread = new Thread(new Runnable() {
                public void run() {
                    writeAudioDataToFile();
                }
            }, "mAudioRecordingThread Thread");

            mAudioRecordingThread.start();
            mHandler.postDelayed(mTimeoutRecordRunnble, MAX_RECORDING_TIME);
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        updateRecordingButton();
    }

    private void stopRecording() {
        LogUtil.d(TAG, "stopRecording()");
        mHandler.removeCallbacks(mTimeoutRecordRunnble);
        mRecording = false;
        if (mAudioRecorder != null) {
            mAudioRecorder.stop();
            mAudioRecorder.release();
            mAudioRecorder = null;
        }

        mAudioRecordingThread = null;

        mAudioFilePath = mRecordFilePath;
        copyWaveFile(mAudioFilePath_Temp, mAudioFilePath);

        ///pcm file not delete
        /*File f = new File(mAudioFilePath_Temp);
        if (f.exists()){
            f.delete();
        }*/

        updateRecordingButton();
    }
    private void writeAudioDataToFile() {
        byte[] data = new byte[mRecBufferSize];
        int read = 0;

        FileOutputStream os = null;

        try {
            File file = new File(mAudioFilePath_Temp);
            if (file.exists()) {
                file.delete();
            }

            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (os != null) {
            while (mRecording) {
                read = mAudioRecorder.read(data, 0, mRecBufferSize);
                if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                    try {
                        os.write(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyWaveFile(String inFilename, String outFilename) {
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;///录音数据长度 +（44 -8） （4个字节）
        long longSampleRate = RECORDER_SAMPLERATE;
        int channels = 1;///声道数，1为单声道，2为多声道
        long byteRate = RECORDER_BPP * RECORDER_SAMPLERATE * channels / 8;
        byte[] data = new byte[mRecBufferSize];
        try {
            in = new FileInputStream(inFilename);
            out = new FileOutputStream(outFilename);
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
                    longSampleRate, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                     long totalDataLen, long longSampleRate, int channels, long byteRate)
            throws IOException {
        int bps = RECORDER_BPP; /// bits per sample
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (channels * bps / 8); // block align;每个采样需要的字节数，计算公式：声道数 * 每个采样需要的bit  / 8
        header[33] = 0;
        header[34] = (byte) bps; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }
}
