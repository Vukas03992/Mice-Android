package com.vukasin_prvulovic.micegame.domain.base.interaction;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public interface Interactor<T> {

    Observable<T> execute(Object... params);
    Observable<T> execute();
    void execute(DisposableObserver<T> observer,Object... params);
    void execute(DisposableObserver<T> observer);
}
