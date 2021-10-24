package com.example.snapchatcopy;

public class Snap {
    String sender;
    String receiver;
    boolean read;
    public Snap(String sender, String receiver) {
        this.sender =  sender;
        this.receiver = receiver;
        this.read = false;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
