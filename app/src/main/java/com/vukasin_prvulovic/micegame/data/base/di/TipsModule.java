package com.vukasin_prvulovic.micegame.data.base.di;

import com.vukasin_prvulovic.micegame.data.repository.base.TipsRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.TipsRepositoryImpl;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class TipsModule {

    @PerApplication
    @Provides
    TipsRepository provideTipsRepository(TipsRepositoryImpl repository){
        return repository;
    }
}
