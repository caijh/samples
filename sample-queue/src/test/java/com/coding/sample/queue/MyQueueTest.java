package com.coding.sample.queue;


import org.junit.Test;

public class MyQueueTest {

    @Test
    public void add() {
        MyQueue queue = new MyQueue(10);
        queue.put("a");
        queue.put("b");
        queue.put("c");
        Thread t1 = new Thread(() -> {
            while (true) {
                for (int i = 0; i < 100; i++) {
                    queue.put(String.valueOf(i));
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            while (true) {
                queue.take();
            }
        }, "t2");
        t1.start();
        t2.start();
    }

}
