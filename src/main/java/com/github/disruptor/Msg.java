package com.github.disruptor;

public class Msg {
    
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int aId) {
        this.id = aId;
    }

    @Override
    public String toString() {
        return "Msg [id=" + this.id + "]";
    }

}
