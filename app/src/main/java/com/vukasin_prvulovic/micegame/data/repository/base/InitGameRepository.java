package com.vukasin_prvulovic.micegame.data.repository.base;

import io.reactivex.Observable;

public interface InitGameRepository {

    Observable<Boolean> clearGameHistory();

}
