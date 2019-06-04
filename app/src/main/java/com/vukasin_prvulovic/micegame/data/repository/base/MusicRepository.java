package com.vukasin_prvulovic.micegame.data.repository.base;

import io.reactivex.Observable;

public interface MusicRepository {

    Observable<Boolean> getMusicPermission();
    Observable<Boolean> getSoundPermission();
    Observable<Boolean> saveMusicPermission(Boolean permission);
    Observable<Boolean> saveSoundPermission(Boolean permission);

}
