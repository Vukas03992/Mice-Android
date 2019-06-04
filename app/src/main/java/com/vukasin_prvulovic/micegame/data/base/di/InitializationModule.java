package com.vukasin_prvulovic.micegame.data.base.di;

import android.content.Context;

import com.vukasin_prvulovic.micegame.data.initializers.FieldInitializer;
import com.vukasin_prvulovic.micegame.data.initializers.GameInitializer;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class InitializationModule {

    @PerApplication
    @Provides
    GameInitializer provideGameInitializer(FieldInitializer fieldInitializer,Context context){
        return new GameInitializer(fieldInitializer,context);
    }
}
