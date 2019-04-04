package com.baigui.simplehandler;

public class LoopThread extends Thread {
    private Looper l ;
    public LoopThread(){
        l = new Looper();
    }

    @Override
    public void run() {
        l.Loop();
    }

    public Looper getL(){
        return l;
    }
}
