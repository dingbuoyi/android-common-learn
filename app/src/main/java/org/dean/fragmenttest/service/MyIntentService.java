package org.dean.fragmenttest.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Dean on 14-12-25.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //耗时任务会排队执行哦
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("----耗时任务开始执行---");
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("----耗时任务执行完成---");
    }
}
