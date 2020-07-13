package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.R;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class PlayerActivity extends AppCompatActivity {
     Button btn_next, btn_previous, btn_pause;
     TextView songTextLabel;
     SeekBar songSeekbar;
     String sname;
     static MediaPlayer myMediaPlayer;
     int position=0;
     ArrayList<File> mySongs;
     Thread updateSeekBar;
     ImageView shuffle, repeat;
    static boolean shuffleBoolean = false, repeateBoolean = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btn_next = (Button)findViewById(R.id.next);
        btn_previous = (Button)findViewById(R.id.previous);
        btn_pause = (Button)findViewById(R.id.pause);
        songTextLabel = (TextView)findViewById(R.id.txtSongLabel);
        songSeekbar = (SeekBar)findViewById(R.id.seekBar);
        shuffle = (ImageView)findViewById(R.id.shuffle);
        repeat = (ImageView)findViewById(R.id.repeat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Now Playing");


        updateSeekBar=new Thread(){
            @Override
            public void run(){
                int totalDuration = myMediaPlayer.getDuration();
                int currentPosition = 0;
                while(currentPosition < totalDuration){
                    try{
                        sleep(500);
                        currentPosition=myMediaPlayer.getCurrentPosition();
                        songSeekbar.setProgress(currentPosition);
                    }
                    catch (InterruptedException e){
                          e.printStackTrace();
                    }
                }
            }
        };

        if(myMediaPlayer != null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }
        Intent i = getIntent();
        Bundle b = i.getExtras();


        mySongs = (ArrayList) b.getParcelableArrayList("songs");

        sname = mySongs.get(position).getName().toString();

        String SongName = i.getStringExtra("songname");
        songTextLabel.setText(SongName);
        songTextLabel.setSelected(true);

        position = b.getInt("pos",0);

        Uri u = Uri.parse(mySongs.get(position).toString());

        myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
        myMediaPlayer.start();
        songSeekbar.setMax(myMediaPlayer.getDuration());
        updateSeekBar.start();

        songSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songSeekbar.setMax(myMediaPlayer.getDuration());
                if(myMediaPlayer.isPlaying()){
                    btn_pause.setBackgroundResource(R.drawable.icon_play);
                    myMediaPlayer.pause();

                }
                else {
                    btn_pause.setBackgroundResource(R.drawable.icon_pause);
                    myMediaPlayer.start();
                }


            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myMediaPlayer.isPlaying()) {
                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                    if (shuffleBoolean && !repeateBoolean) {
                        position = getRandom( mySongs.size() - 1);
                    } else if (!shuffleBoolean && !repeateBoolean) {
                        position = ((position + 1) % mySongs.size());
                    }


                    Uri u = Uri.parse(mySongs.get(position).toString());

                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

                    sname = mySongs.get(position).getName().toString();
                    songTextLabel.setText(sname);
                    myMediaPlayer.start();
                }
                else {
                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                    if (shuffleBoolean && !repeateBoolean) {
                        position = getRandom( mySongs.size() - 1);
                    } else if (!shuffleBoolean && !repeateBoolean) {
                        position = ((position + 1) % mySongs.size());
                    }

                    Uri u = Uri.parse(mySongs.get(position).toString());
                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                    sname = mySongs.get(position).getName().toString();
                    songTextLabel.setText(sname);


                }
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myMediaPlayer.isPlaying()) {
                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                    if (shuffleBoolean && !repeateBoolean) {
                        position = getRandom( mySongs.size() - 1);
                    } else if (!shuffleBoolean && !repeateBoolean) {
                        position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
                    }

                    Uri u = Uri.parse(mySongs.get(position).toString());
                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                    sname = mySongs.get(position).getName().toString();
                    songTextLabel.setText(sname);
                    myMediaPlayer.start();
                }
                else {
                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                    if (shuffleBoolean && !repeateBoolean) {
                        position = getRandom( mySongs.size() - 1);
                    } else if (!shuffleBoolean && !repeateBoolean) {
                        position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
                    }

                    Uri u = Uri.parse(mySongs.get(position).toString());
                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                    sname = mySongs.get(position).getName().toString();
                    songTextLabel.setText(sname);


                }
            }



        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (shuffleBoolean){
                     shuffleBoolean = false;
                     shuffle.setImageResource(R.drawable.ic_shuffle);

                 }
                 else {
                     shuffleBoolean = true;
                     shuffle.setImageResource(R.drawable.ic_shuffle_on);
                 }

            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (repeateBoolean){
                    repeateBoolean = false;
                    repeat.setImageResource(R.drawable.ic_repeat);
                }
                else {
                    repeateBoolean = true;
                    repeat.setImageResource(R.drawable.ic_repeat_on);
                }

            }
        });






    }

    private int getRandom(int i) {
        Random random = new Random();

        return random.nextInt(i+1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
