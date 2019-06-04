package com.vukasin_prvulovic.micegame.data.repository.repositories;

import com.vukasin_prvulovic.micegame.data.local.manager.TipsManager;
import com.vukasin_prvulovic.micegame.data.repository.base.TipsRepository;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerApplication
public class TipsRepositoryImpl implements TipsRepository{

    @Inject
    TipsManager manager;

    @Inject
    public TipsRepositoryImpl(TipsManager manager) {
        this.manager = manager;
    }

    @Override
    public Observable<Boolean> saveTipsPermission(boolean permission) {
        return Observable.just(manager.saveTipsPermission(permission));
    }

    @Override
    public Observable<Boolean> getTipsPermission() {
        return Observable.just(manager.getTipsPermission());
    }
}
