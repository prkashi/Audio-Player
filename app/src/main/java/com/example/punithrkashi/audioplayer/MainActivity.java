package com.example.punithrkashi.audioplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mPlayer;

    public void playMusic(View view){

        mPlayer.start();
    }

    public void pauseMusic(View view){

        mPlayer.pause();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = MediaPlayer.create(this, R.raw.music);

        final AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar skbar = (SeekBar)findViewById(R.id.skbar);
        final SeekBar skbar2 = (SeekBar)findViewById(R.id.skbar2);
        skbar.setMax(maxVolume);
        skbar2.setMax(mPlayer.getDuration());
        skbar.setProgress(currVolume);

        skbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                audio.setStreamVolume(AudioManager.STREAM_MUSIC, i,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                skbar2.setProgress(mPlayer.getCurrentPosition());

            }
        },0,2000);

        skbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                mPlayer.seekTo(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
