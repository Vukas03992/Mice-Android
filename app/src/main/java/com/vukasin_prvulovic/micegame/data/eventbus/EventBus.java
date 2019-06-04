package com.vukasin_prvulovic.micegame.data.eventbus;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class EventBus<T> {

    private PublishSubject<T> publishSubject;

    public EventBus() {
        publishSubject=PublishSubject.create();
    }

    public void send(T object){
        publishSubject.onNext(object);
    }

    public Observable<T> subscribe(){
        return publishSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
