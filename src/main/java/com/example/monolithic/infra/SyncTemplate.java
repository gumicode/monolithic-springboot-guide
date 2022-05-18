package com.example.monolithic.infra;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncTemplate {

    private long start = System.currentTimeMillis();
    private int cnt = 0;

    private SyncTemplate() {
    }

    private static class SingletonHolder {
        private static final SyncTemplate INSTANCE = new SyncTemplate();
    }

    public static SyncTemplate getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public synchronized void print(){
        cnt += 1;
        if(cnt == 2000) {
            long end = System.currentTimeMillis();
            log.info(end - start + " 밀리초");
        }
    }
}
