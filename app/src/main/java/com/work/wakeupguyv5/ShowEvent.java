package com.work.wakeupguyv5;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class ShowEvent extends Activity implements View.OnClickListener{

    private PowerManager pm;
    private PowerManager.WakeLock wl;
    private KeyguardManager km;
    private KeyguardLock kl;
    private Ringtone r;
    private Button btnStop;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.i("showEvent","onCreate()in DismissLock");
        pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        km = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        kl = km.newKeyguardLock("ShowEvent");
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"ShowEnvet");
        wl.acquire();
        kl.disableKeyguard();
        setContentView(R.layout.sec);

        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStop){
            AlarmClockActivity.listValue.remove(0);
            this.finish();
            startActivity(new Intent(ShowEvent.this,AlarmClockActivity.class));

        }
    }
    @Override
    protected void onResume() {

        super.onResume();
        wl.acquire();//must call this!
        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if(notif==null){
            notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if(notif==null){
                notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        r = RingtoneManager.getRingtone(getApplicationContext(), notif);
        r.play();

    }

    @Override
    public void onPause(){
        super.onPause();
        wl.release();
        if(r.isPlaying()){
            r.stop();
        }
    }

}
