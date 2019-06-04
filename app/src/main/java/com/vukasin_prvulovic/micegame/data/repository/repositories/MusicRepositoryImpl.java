package com.vukasin_prvulovic.micegame.data.repository.repositories;

import com.vukasin_prvulovic.micegame.data.local.manager.MusicManager;
import com.vukasin_prvulovic.micegame.data.repository.base.MusicRepository;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerApplication
public class MusicRepositoryImpl implements MusicRepository{

    @Inject
    MusicManager manager;

    @Inject
    public MusicRepositoryImpl() {
    }

    @Override
    public Observable<Boolean> getMusicPermission() {
        return manager.getMusicPermission();
    }

    @Override
    public Observable<Boolean> getSoundPermission() {
        return manager.getSoundPermission();
    }

    @Override
    public Observable<Boolean> saveMusicPermission(Boolean permission) {
        return manager.saveMusicPermission(permission);
    }

    @Override
    public Observable<Boolean> saveSoundPermission(Boolean permission) {
        return manager.saveSoundPermission(permission);
    }
}
