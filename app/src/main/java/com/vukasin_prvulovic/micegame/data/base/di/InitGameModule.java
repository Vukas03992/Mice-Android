package com.vukasin_prvulovic.micegame.data.base.di;

import com.vukasin_prvulovic.micegame.data.repository.base.InitGameRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.InitGame;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

@PerFragment
@Module
public class InitGameModule {

    @PerFragment
    @Provides
    InitGameRepository provideInitGameRepository(InitGame initGame){
        return initGame;
    }
}
