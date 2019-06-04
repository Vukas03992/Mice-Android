package com.vukasin_prvulovic.micegame.data.repository.repositories;

import com.vukasin_prvulovic.micegame.data.local.manager.PlayerNameManager;
import com.vukasin_prvulovic.micegame.data.repository.base.SaveReadPlayerNameRepository;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerApplication
public class SaveReadPlayerName implements SaveReadPlayerNameRepository {

    @Inject
    PlayerNameManager manager;

    @Inject
    public SaveReadPlayerName(){}

    @Override
    public Observable<Boolean> savePlayersNames(String playerOne, String playerTwo) {
        return manager.savePlayersNames(playerOne,playerTwo);
    }

    @Override
    public Observable<String[]> readPlayersNames() {
        return manager.readePlayersNames();
    }
}
