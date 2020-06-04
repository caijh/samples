package com.github.caijh.sample.drools.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.kie.api.runtime.KieContainer;

public class KieUtils {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static KieContainer kieContainer;

    private KieUtils() {

    }

    public static KieContainer getKieContainer() {
        LOCK.readLock().lock();
        try {
            return kieContainer;
        } finally {
            LOCK.readLock().unlock();
        }
    }

    public static void setKieContainer(KieContainer kieContainer) {
        LOCK.writeLock().lock();
        try {
            KieUtils.kieContainer = kieContainer;
        } finally {
            LOCK.writeLock().unlock();
        }
    }

}
