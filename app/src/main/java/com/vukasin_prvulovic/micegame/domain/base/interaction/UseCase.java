package com.vukasin_prvulovic.micegame.domain.base.interaction;

import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T> implements Interactor<T> {

    private ThreadExecutor executor;
    private PostExecutionThread postExecutionThread;
    private CompositeDisposable compositeDisposable;

    public UseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread) {
        this.executor = executor;
        this.postExecutionThread = postExecutionThread;
        compositeDisposable=new CompositeDisposable();
    }

    protected abstract Observable<T> buildUseCaseObservable(Object... params);

    private Observable<T> buildUseCaseObservable(){
        return buildUseCaseObservable((Object) null);
    }

    @Override
    public Observable<T> execute(Object... params) {
        return buildUseCaseObservable(params);
    }

    @Override
    public Observable<T> execute() {
        return buildUseCaseObservable();
    }

    @Override
    public void execute(DisposableObserver<T> observer, Object... params) {
        compositeDisposable.add(buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer));
    }

    @Override
    public void execute(DisposableObserver<T> observer) {
        compositeDisposable.add(buildUseCaseObservable()
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer));
    }

    public void dispose(){
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
