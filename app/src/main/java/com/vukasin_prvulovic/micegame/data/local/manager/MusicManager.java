package com.vukasin_prvulovic.micegame.data.local.manager;

import android.content.SharedPreferences;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

import static com.vukasin_prvulovic.micegame.data.utils.Constants.MUSIC;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.SHARED_PREFERENCES_MUSIC;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.SOUND;

@PerApplication
public class MusicManager {

    private SharedPreferences sharedPreferences;
    @Inject
    @Named("music")
    EventBus<Boolean> eventBusMusic;
    @Inject
    @Named("sound")
    EventBus<Boolean> eventBusSound;

    @Inject
    public MusicManager(@Named(SHARED_PREFERENCES_MUSIC) SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Observable<Boolean> getMusicPermission(){
        return Observable.just(sharedPreferences.getBoolean(MUSIC,true));
    }

    public Observable<Boolean> getSoundPermission(){
        return Observable.just(sharedPreferences.getBoolean(SOUND,true));
    }

    public Observable<Boolean> saveMusicPermission(Boolean permission){
        sharedPreferences.edit()
                .putBoolean(MUSIC,permission)
                .apply();
        eventBusMusic.send(permission);
        return Observable.just(permission);
    }

    public Observable<Boolean> saveSoundPermission(Boolean permission){
        sharedPreferences.edit()
                .putBoolean(SOUND,permission)
                .apply();
        eventBusSound.send(permission);
        return Observable.just(permission);
    }

}
