package com.example.a25_16028881_nguyenngocnhien;

import static com.example.a25_16028881_nguyenngocnhien.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.a25_16028881_nguyenngocnhien.model.Song;

public class MyService extends Service {

    private static final int ACTION_PAUSE = 1;
    private static final int ACTION_RESUME = 2;
    private static final int ACTION_CLEAR = 3;


    private MediaPlayer mMediaPlayer;
    private boolean isPlaying;
    private Song mSong;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Tincoder", "My Service onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getSerializableExtra("object_song") != null) {
            Song song = (Song) intent.getSerializableExtra("object_song");

            if (song != null) {
                mSong = song;
                startMusic(song);
                Log.e("TAG", "onStartCommand: ");
            }
        }

        int actionMusic = intent.getIntExtra("action_music_service", 0);
        Log.e("Hoang", "abbbbb" + actionMusic);
        handleActionMusic(actionMusic);

        return START_NOT_STICKY;
    }

    private void startMusic(Song song) {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResourece());
        }
        mMediaPlayer.start();
        isPlaying = true;
    }

    private void handleActionMusic(int action) {
        switch (action) {
            case ACTION_PAUSE:
                pauseMusic();
                break;
            case ACTION_RESUME:
                resumMusic();
                break;
            case ACTION_CLEAR:
                stopSelf();
                break;
        }
    }

    private void pauseMusic() {
        if (mMediaPlayer != null && isPlaying == true) {
            mMediaPlayer.pause();
            isPlaying = false;
        }

    }

    private void resumMusic() {
        if (mMediaPlayer != null && isPlaying == false) {
            mMediaPlayer.start();
            isPlaying = true;
        }
    }
}