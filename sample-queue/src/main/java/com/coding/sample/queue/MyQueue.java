package com.coding.sample.queue;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

    private final LinkedList<Object> list = new LinkedList<>();

    private final AtomicInteger count = new AtomicInteger(0);

    private final int minSize = 0;

    private final int maxSize;

    private final Object lock = new Object();

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(Object object) {
        synchronized (lock) {
            while (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("加入元素：" + object);
            list.add(object);
            count.incrementAndGet();
            lock.notifyAll();
        }
    }

    public void take() {
        synchronized (lock) {
            while (count.get() == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Object o = list.removeFirst();
            System.out.println("移除元素" + o);
            count.decrementAndGet();
            lock.notifyAll();
        }
    }


}
