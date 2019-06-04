package com.vukasin_prvulovic.micegame.data.repository.base;

import io.reactivex.Observable;

public interface SaveReadPlayerNameRepository {

    Observable<Boolean> savePlayersNames(String playerOne, String playerTwo);
    Observable<String[]> readPlayersNames();
}
