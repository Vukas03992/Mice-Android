package com.vukasin_prvulovic.micegame.data.base.di;

import com.vukasin_prvulovic.micegame.data.repository.base.MusicRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.MusicRepositoryImpl;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import dagger.Module;
import dagger.Provides;

@PerApplication
@Module
public class MusicModule {

    @PerApplication
    @Provides
    MusicRepository provideMusicRepository(MusicRepositoryImpl musicRepository){
        return musicRepository;
    }
}
