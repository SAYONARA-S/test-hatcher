package com.hatcher.app.IM;


import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by wesley_s on 16/5/10.
 */
public class VoiceRecord {
        static final private double EMA_FILTER = 0.6;

        private MediaRecorder mRecorder = null;
        private double mEMA = 0.0;

        public void start(String name) {
            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                return;
            }
            if (mRecorder == null) {
                mRecorder = new MediaRecorder();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                File dirFile = new File(Environment.getExternalStorageDirectory()+"/amrcache/");
                if(!dirFile.exists()){
                    dirFile.mkdir();
                }
                mRecorder.setOutputFile(Environment.getExternalStorageDirectory()+"/amrcache/"+name);
                try {
                    mRecorder.prepare();
                    mRecorder.start();

                    mEMA = 0.0;
                } catch (IllegalStateException e) {
                    System.out.print(e.getMessage());
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }

            }
        }

        public void stop() {
            if (mRecorder != null) {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
            }
        }

        public void pause() {
            if (mRecorder != null) {
                mRecorder.stop();
            }
        }

        public void start() {
            if (mRecorder != null) {
                mRecorder.start();
            }
        }

        public double getAmplitude() {
            if (mRecorder != null)
                return (mRecorder.getMaxAmplitude() / 2700.0);
            else
                return 0;

        }

        public double getAmplitudeEMA() {
            double amp = getAmplitude();
            mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
            return mEMA;
        }


}
