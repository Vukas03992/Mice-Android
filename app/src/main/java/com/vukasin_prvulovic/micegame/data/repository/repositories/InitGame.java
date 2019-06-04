package com.vukasin_prvulovic.micegame.data.repository.repositories;

import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.repository.base.InitGameRepository;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class InitGame implements InitGameRepository{

    @Inject
    GameSnapshotManager gameSnapshotManager;

    @Inject
    public InitGame() {
    }

    @Override
    public Observable<Boolean> clearGameHistory() {
        return gameSnapshotManager.clearGameSnapshots();
    }
}
