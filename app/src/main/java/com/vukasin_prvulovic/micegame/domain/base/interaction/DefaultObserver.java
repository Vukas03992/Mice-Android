package com.vukasin_prvulovic.micegame.domain.base.interaction;

import io.reactivex.observers.DisposableObserver;

public class DefaultObserver<T> extends DisposableObserver<T>{
    @Override
    public void onNext(T t) {

    }
    @Override
    public void onError(Throwable e) {}

    @Override
    public void onComplete() {}
}
