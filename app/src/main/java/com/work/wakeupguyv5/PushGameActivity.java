package com.work.wakeupguyv5;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import static com.work.wakeupguyv5.R.drawable.giphy;

public class PushGameActivity extends AppCompatActivity {
    private TextView score,countc;
    private pl.droidsonroids.gif.GifImageView gif;
    private Button push;
    public int count= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_game);

        push = (Button)findViewById(R.id.push);
        score = (TextView)findViewById(R.id.score);
        countc = (TextView)findViewById(R.id.countc);
        gif = (pl.droidsonroids.gif.GifImageView)findViewById(R.id.gif);
        final MediaPlayer songbutton = MediaPlayer.create(this, R.raw.songbutton2);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n = Integer.valueOf(score.getText().toString()) + 1;
                Handler handler = new Handler();
                songbutton.start();
                int countcolor = 1 + Integer.valueOf(countc.getText().toString());
                switch (countcolor){
                    case 1:
                        score.setTextColor(Color.rgb(255,0,0));
                        break;
                    case 2:
                        score.setTextColor(Color.rgb(255,51,0));
                        break;
                    case 3:
                        score.setTextColor(Color.rgb(255,255,0));
                        break;
                    case 4:
                        score.setTextColor(Color.rgb(0,153,51));
                        break;
                    case 5:
                        score.setTextColor(Color.rgb(0,153,255));
                        break;
                    case 6:
                        score.setTextColor(Color.rgb(51,51,153));
                        break;
                    case 7:
                        score.setTextColor(Color.rgb(204,0,204));
                        break;
                }
                countc.setText(""+countcolor);
                if(countcolor == 7){
                    countc.setText("0");
                }

                if(n == 1){
                    score.setTextSize(80);
                    gif.setImageResource(R.drawable.bp2);
//                    handler.postDelayed(new Runnable() {
//                        public void run() {
//                            // yourMethod();
//                        }
//                    }, 500);   //2 seconds
                }else if(n % 2 == 0){

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            gif.setImageResource(R.drawable.bp4);// yourMethod();
                            score.setTextSize(75);
                        }
                    }, 200);   //2 seconds
                    gif.setImageResource(R.drawable.bp3);
                    score.setTextSize(50);
                }
                else if(n % 2 == 1 && n != 1){

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            gif.setImageResource(R.drawable.bp4);// yourMethod();
                            score.setTextSize(75);
                        }
                    }, 200);   //2 seconds
                    gif.setImageResource(R.drawable.bp3);
                    score.setTextSize(50);
                }
                score.setText(""+n);
                if(n == 150){

                }

            }
        });
    }
}
