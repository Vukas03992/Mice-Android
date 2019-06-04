package com.vukasin_prvulovic.micegame.presentation.view.activities;

import android.content.Context;
import android.media.MediaPlayer;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.domain.base.interaction.DefaultObserver;
import com.vukasin_prvulovic.micegame.domain.usecases.GetMusicPermissionUseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

@PerActivity
public class MusicPlayer {

    private MediaPlayer musicPlayer;
    private Context context;
    @Inject
    GetMusicPermissionUseCase getMusicPermissionUseCase;
    @Inject
    @Named("music")
    EventBus<Boolean> eventBus;

    private CompositeDisposable disposable;

    private boolean permission;

    @Inject
    public MusicPlayer(Context context) {
        this.context = context;
    }

    public void onStart(){
        getMusicPermissionUseCase.execute(new MusicObserver());
        musicPlayer=MediaPlayer.create(context, R.raw.background_music);
        musicPlayer.setLooping(true);
        disposable=new CompositeDisposable();
        subscribe();
    }

    public void onStop(){
        musicPlayer.release();
        musicPlayer=null;
        getMusicPermissionUseCase.dispose();
        disposable.dispose();
    }

    public void playMusic(){
        if (permission)
        musicPlayer.start();
    }

    public boolean isPlaying(){
        return musicPlayer.isPlaying();
    }

    public void stopMusic(){
        musicPlayer.stop();
        musicPlayer.release();
        musicPlayer=MediaPlayer.create(context,R.raw.background_music);
        musicPlayer.setLooping(true);
    }

    private void subscribe(){
        disposable.add(eventBus.subscribe()
        .subscribeWith(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                permission=aBoolean;
                if (musicPlayer.isPlaying()){
                    stopMusic();
                }
                playMusic();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }));
    }

    private class MusicObserver extends DefaultObserver<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            permission=aBoolean;
            playMusic();
        }
    }
}
