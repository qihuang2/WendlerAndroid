package com.android.wendler.wendlerandroid.testUtils;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by QiFeng on 7/3/17.
 */

public class ImmediateScheduler extends Scheduler{


    public static void initSchedulers(){
        final ImmediateScheduler scheduler = new ImmediateScheduler();

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@io.reactivex.annotations.NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });

        RxJavaPlugins.setInitIoSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@io.reactivex.annotations.NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return scheduler;
            }
        });
    }

    @Override
    public Worker createWorker() {
        return new ExecutorScheduler.ExecutorWorker(new Executor() {
            @Override
            public void execute(@NonNull Runnable runnable) {
                runnable.run();
            }
        });
    }

}
