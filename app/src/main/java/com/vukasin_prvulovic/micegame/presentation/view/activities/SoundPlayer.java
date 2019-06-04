package com.vukasin_prvulovic.micegame.presentation.view.activities;

import android.content.Context;
import android.media.MediaPlayer;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.domain.base.interaction.DefaultObserver;
import com.vukasin_prvulovic.micegame.domain.usecases.GetSoundPermissionUseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

@PerActivity
public class SoundPlayer {

    private MediaPlayer takeTokenSoundEffect;
    private MediaPlayer putTokenSoundEffect;
    private MediaPlayer miceSoundEffect;
    private Context context;
    @Inject
    GetSoundPermissionUseCase getSoundPermissionUseCase;
    @Inject
    @Named("sound")
    EventBus<Boolean> eventBus;

    private CompositeDisposable compositeDisposable;

    private boolean permission;

    @Inject
    public SoundPlayer(Context context) {
        this.context=context;
    }

    public void onStart(){
        compositeDisposable=new CompositeDisposable();
        takeTokenSoundEffect=MediaPlayer.create(context, R.raw.take_token_sound_effect);
        putTokenSoundEffect=MediaPlayer.create(context,R.raw.put_token_sound_effect);
        miceSoundEffect=MediaPlayer.create(context,R.raw.mice_sound_effect);
        getSoundPermissionUseCase.execute(new SoundObserver());
        subscribe();
    }

    public void onStop(){
        takeTokenSoundEffect.release();
        putTokenSoundEffect.release();
        miceSoundEffect.release();
        takeTokenSoundEffect=null;
        putTokenSoundEffect=null;
        miceSoundEffect=null;
        getSoundPermissionUseCase.dispose();
        compositeDisposable.dispose();
    }

    public void playTakeEffect(){
        if (permission)
        takeTokenSoundEffect.start();
    }

    public void playPutEffect(){
        if (permission)
        putTokenSoundEffect.start();
    }

    public void playMiceEffect(){
        if (permission)
        miceSoundEffect.start();
    }

    private void subscribe(){
        compositeDisposable.add(eventBus.subscribe()
                                            .subscribeWith(new DisposableObserver<Boolean>() {
                                                @Override
                                                public void onNext(Boolean aBoolean) {
                                                    permission=aBoolean;
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            }));
    }

    private class SoundObserver extends DefaultObserver<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            permission=aBoolean;
        }
    }
}
