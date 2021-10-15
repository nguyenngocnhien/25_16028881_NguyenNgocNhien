package com.example.a25_16028881_nguyenngocnhien;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.a25_16028881_nguyenngocnhien.model.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    SeekBar mSeekBarTime, mSeekBarVol;
    static MediaPlayer sMediaPlayer;
    private Runnable mRunnable;
    int currentIndex = 0;

    RecyclerView rcv;
    CustomRecyclerView adt;
    List<Song> mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.rcv);
        mSongs = new ArrayList<Song>();

        mSongs.add(new Song("Cầu Vòng Khuyết","Tuấn Hưng",R.drawable.tuanhung,R.raw.cauvongkhuyet_tuanhung));
        mSongs.add(new Song("Gặp Gỡ Yêu","Phan Mạnh Quỳnh",R.drawable.phanmanhquynh,R.raw.gapgoyeuthuongvaduocbenem));
        mSongs.add(new Song("Một Thời Đã Xa","Thùy Chi",R.drawable.thuychi,R.raw.motthoidaxa));
        mSongs.add(new Song("Ta Là Của Nhau","Đông Nhi",R.drawable.dongnhi,R.raw.talacuanhau));
        mSongs.add(new Song("Thương Người Dưng","Noo Phước Thịnh",R.drawable.noophuocthinh,R.raw.thuongmaycunglanguoidung));
        mSongs.add(new Song("Tình Thôi Xót Xa","Lam Trường",R.drawable.lamtruong,R.raw.tinhthoixotxa_lamtruong));


        adt = new CustomRecyclerView(mSongs,this);
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adt);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void clickItem(Song song) {
        Intent intent  = new Intent(MainActivity.this,Item.class);
        intent.putExtra("song",song);
        intent.putExtra("listMusic", (Serializable) mSongs);
        intent.putExtra("index",mSongs.indexOf(song));
        startActivity(intent);
    }
}