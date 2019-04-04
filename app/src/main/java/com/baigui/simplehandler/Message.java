package com.baigui.simplehandler;

public abstract class Message implements Runnable {

    public Message next;

    public long delay;
    public Runnable run;
}
