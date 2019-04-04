package com.baigui.simplehandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LoopThread t = new LoopThread();
        t.start();
        Log.e("-------------", "!!!!!!!!!!!!!!1");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("-------------", "!!!!!!!!!!!!!!2");
                t.getL().postRunabledealy(new Message() {
                    @Override
                    public void run() {
                        Log.e("-------------", "!!!!!!!!!!!!!!3");
                        t.getL().postRunabledealy(new Message() {
                            @Override
                            public void run() {
                                Log.e("-------------", "!!!!!!!!!!!!!!5");
                            }
                        }, 4000);
                    }
                }, 2000);

                t.getL().postRunabledealy(new Message() {
                    @Override
                    public void run() {
                        Log.e("-------------", "!!!!!!!!!!!!!!4");
                    }
                }, 4000);
            }
        }, 1000);

    }

}
