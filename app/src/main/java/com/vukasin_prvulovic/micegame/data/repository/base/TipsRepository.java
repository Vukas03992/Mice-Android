package com.vukasin_prvulovic.micegame.data.repository.base;

import io.reactivex.Observable;

public interface TipsRepository {

    Observable<Boolean> saveTipsPermission(boolean permission);
    Observable<Boolean> getTipsPermission();
}
