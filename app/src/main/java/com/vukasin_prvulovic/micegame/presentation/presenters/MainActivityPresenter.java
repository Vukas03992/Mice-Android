package com.vukasin_prvulovic.micegame.presentation.presenters;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;
import com.vukasin_prvulovic.micegame.presentation.base.presenter.Presenter;
import com.vukasin_prvulovic.micegame.presentation.view.activities.MusicPlayer;
import com.vukasin_prvulovic.micegame.presentation.view.activities.SoundPlayer;
import com.vukasin_prvulovic.micegame.presentation.view.activities.abstraction.MainActivityView;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

@PerActivity
public class MainActivityPresenter extends Presenter {

    private MainActivityView view;
    private EventBus<Transaction> eventBus;
    private CompositeDisposable compositeDisposable;
    @Inject
    MusicPlayer musicPlayer;
    @Inject
    SoundPlayer soundPlayer;
    @Inject
    @Named("exit")
    EventBus<Boolean> eventBusExit;

    @Inject
    public MainActivityPresenter(MainActivityView view,@Named("transactions") EventBus<Transaction> eventBus, CompositeDisposable compositeDisposable) {
        this.view = view;
        this.eventBus = eventBus;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onStart() {
        view.initMenu();
        subscribe();
        subscribeExit();
        musicPlayer.onStart();
        soundPlayer.onStart();
    }

    @Override
    public void onStop() {
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.clear();
        }
        musicPlayer.onStop();
        soundPlayer.onStop();
    }

    public void stopMusic(){
        musicPlayer.stopMusic();
    }

    public void playMusic(){
        if (!musicPlayer.isPlaying()){
            musicPlayer.playMusic();
        }
    }

    private void subscribe(){
        compositeDisposable.add(eventBus.subscribe()
                                    .subscribeWith(new DisposableObserver<Transaction>(){
                                        @Override
                                        public void onNext(Transaction transaction) {
                                            if (transaction==Transaction.BOARD_FRAGMENT){
                                                musicPlayer.stopMusic();
                                                musicPlayer.playMusic();
                                            }
                                            view.doTransaction(transaction);
                                        }
                                        @Override
                                        public void onError(Throwable e) {}
                                        @Override
                                        public void onComplete() {}
                                    }));
    }

    private void subscribeExit(){
        compositeDisposable.add(eventBusExit.subscribe()
                                                .subscribeWith(new DisposableObserver<Boolean>() {
                                                    @Override
                                                    public void onNext(Boolean aBoolean) {
                                                        if (aBoolean){
                                                            view.onBack();
                                                        }
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onComplete() {

                                                    }
                                                }));
    }
}
