package com.baigui.simplehandler;

import android.app.Notification;
import android.os.SystemClock;
import android.util.Log;

public class Looper {

    private long ptr;

    private Message m;
    public Looper(){
        ptr = Init();
    }


    public void postRunabledealy(Message message,long delay){

        synchronized (this) {
            message.delay = SystemClock.uptimeMillis() + delay;
            if (m == null) {
                m = message;
            } else {
                Message tmp = m;
                Message priv = m;
                while (message.delay > tmp.delay){
                    if (tmp.next == null){
                        tmp.next = message;
                        break;
                    }
                    priv = tmp;
                    tmp = tmp.next;

                }
                //这是插入情况
                if (tmp.next != message){
                    priv.next = message;
                    message.next = tmp;
                }
            }
            Intrupt(ptr);
            Log.e("addrunable", "postRunabledealy:");
        }
    }

    public void Loop(){

        while (true){
            Log.e("loop", "Loop: ");

            if (m == null){
                Wait(ptr, 0);
                continue;
            }
            long now = SystemClock.uptimeMillis();
            //需要立即处理，
            if (now >= m.delay) {
                m.run();
                Log.e("loop", "Loop: after run()");
                if (null == m.next) {
                    m = null;
                } else {
                    m = m.next;
                }
                Log.e("-----------", "Loop: hava remove message");
                continue;
            }

            Wait(ptr, (int) (m.delay-now));


        }
    }


    public native long Init();
    public native void Wait(long ptr,int delay) ;
    public native void Intrupt(long ptr);

}
