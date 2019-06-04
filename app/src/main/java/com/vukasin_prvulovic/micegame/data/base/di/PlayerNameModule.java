package com.vukasin_prvulovic.micegame.data.base.di;

import com.vukasin_prvulovic.micegame.data.repository.base.SaveReadPlayerNameRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.SaveReadPlayerName;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@PerApplication
@Module
public class PlayerNameModule {

    @Provides
    @PerApplication
    SaveReadPlayerNameRepository provideSavePlayerName(SaveReadPlayerName savePlayerName){
        return savePlayerName;
    }
}
