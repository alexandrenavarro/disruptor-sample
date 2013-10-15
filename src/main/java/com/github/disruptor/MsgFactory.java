package com.github.disruptor;

import com.lmax.disruptor.EventFactory;

public class MsgFactory implements EventFactory<Msg> {

    @Override
    public Msg newInstance() {
        return new Msg();
    }


}
