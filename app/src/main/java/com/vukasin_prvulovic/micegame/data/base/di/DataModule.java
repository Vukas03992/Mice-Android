package com.vukasin_prvulovic.micegame.data.base.di;

import com.google.gson.Gson;
import com.vukasin_prvulovic.micegame.data.repository.base.GameRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.GameRepositoryImpl;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.activities.MusicPlayer;

import dagger.Module;
import dagger.Provides;

@PerApplication
@Module(includes = {PlayerNameModule.class,FieldsTagsModule.class,InitializationModule.class, MusicModule.class, TipsModule.class})
public class DataModule {

    @PerApplication
    @Provides
    Gson provideGson(){
        return new Gson();
    }

}
