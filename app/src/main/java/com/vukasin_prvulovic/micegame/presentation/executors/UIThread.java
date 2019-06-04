package com.vukasin_prvulovic.micegame.presentation.executors;

import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread{

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
