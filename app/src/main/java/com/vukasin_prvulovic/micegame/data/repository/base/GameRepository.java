package com.vukasin_prvulovic.micegame.data.repository.base;

import com.vukasin_prvulovic.micegame.data.model.Game;

import io.reactivex.Observable;

public interface GameRepository {

    Observable<Game> play(Game game,String previousField, String nextField);
    Observable<Game> undo();
    Observable<Game> getLastGame();

}
