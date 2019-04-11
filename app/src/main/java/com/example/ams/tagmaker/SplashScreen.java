package com.example.ams.tagmaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        final ImageView logo = (ImageView) findViewById(R.id.imageView18);




        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.pop_up);
        logo.startAnimation(expandIn);




        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    Intent intent = new Intent(SplashScreen.this,Login.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,0);
                    finish();
                }

            }

        };
        timerThread.start();
    }
}
