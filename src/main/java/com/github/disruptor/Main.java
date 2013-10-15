package com.github.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * <p>Main. </p>
 *
 * @author anavarro - Oct 15, 2013
 *
 */
public class Main {
    
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Disruptor<Msg> disruptor = new Disruptor<Msg>(new MsgFactory(), 1024, executorService);
        final EventHandler<Msg> handler = new EventHandler<Msg>() {
            public void onEvent(final Msg msg, final long sequence, final boolean endOfBatch) throws Exception {
                System.out.println(msg);
            }
        };
        disruptor.handleEventsWith(handler);
        final RingBuffer<Msg> ringBuffer = disruptor.start();
        System.out.println("Disruptor.start");
        
        for (int i = 0; i < 10000; i++) {
            
            final long seq = ringBuffer.next();
            final Msg msg = ringBuffer.get(seq);
            msg.setId(i);
            ringBuffer.publish(seq);
        }
        
        System.out.println("Disruptor.stop");
        disruptor.shutdown();
        executorService.shutdown();

    }

}
