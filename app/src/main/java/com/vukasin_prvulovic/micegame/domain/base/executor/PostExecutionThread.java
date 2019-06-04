package com.vukasin_prvulovic.micegame.domain.base.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
