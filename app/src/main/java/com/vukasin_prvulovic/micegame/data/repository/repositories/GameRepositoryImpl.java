package com.vukasin_prvulovic.micegame.data.repository.repositories;

import com.vukasin_prvulovic.micegame.data.local.manager.GameManager;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.repository.base.GameRepository;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class GameRepositoryImpl implements GameRepository{

    @Inject
    GameManager gameManager;

    @Inject
    public GameRepositoryImpl() {
    }

    @Override
    public Observable<Game> play(Game game,String previousField, String nextField) {
        return gameManager.play(game,previousField,nextField);
    }

    @Override
    public Observable<Game> undo() {
        return gameManager.undo();
    }

    @Override
    public Observable<Game> getLastGame() {
        return gameManager.getLastGame();
    }

}
