package com.example.Togo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by lilian on 8/15/14.
 */
public class Splash extends Activity {

    //declaring variables
    float x1,x2;
    float y1,y2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        int SPLASH_TIME=3000;
        Handler hnd= new Handler() {
            @Override
            public void close() {
                finish();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord logRecord) {

            }
        };

    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case    MotionEvent.ACTION_UP:
            {
                x1=touchevent.getX();
                y1=touchevent.getY();
            }
            break;

            case MotionEvent.ACTION_DOWN:
            {
                x2=touchevent.getX();
                y2=touchevent.getY();
            }
            break;

            default:
                break;
        }return false;
    }
}
