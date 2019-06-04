package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent.MainActivityComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import com.vukasin_prvulovic.micegame.presentation.base.view.Application;
import com.vukasin_prvulovic.micegame.presentation.executors.JobExecutor;
import com.vukasin_prvulovic.micegame.presentation.executors.UIThread;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static com.vukasin_prvulovic.micegame.data.utils.Constants.SHARED_PREFERENCES_MUSIC;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.SHARED_PREFERENCES_PLAYER_NAME;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.SHARED_PREFERENCES_TIPS;

@PerApplication
@Module(subcomponents = {MainActivityComponent.class})
public class ApplicationModule {

    @PerApplication
    @Provides
    AssetManager provideAssetManager(Application application){
        return application.getAssets();
    }

    @PerApplication
    @Provides
    Context provideContext(Application application){
        return application.getApplicationContext();
    }

    @PerApplication
    @Provides
    ThreadExecutor provideThreadExecutor(){
        return new JobExecutor();
    }

    @PerApplication
    @Provides
    PostExecutionThread providePostExecutionThread(){
        return new UIThread();
    }

    @PerApplication
    @Provides
    @Named(SHARED_PREFERENCES_PLAYER_NAME)
    SharedPreferences provideSharedPreferences(Application application){
        return application.getSharedPreferences(SHARED_PREFERENCES_PLAYER_NAME,Context.MODE_PRIVATE);
    }

    @PerApplication
    @Provides
    @Named(SHARED_PREFERENCES_MUSIC)
    SharedPreferences provideSharedPreferencesMusic(Application application){
        return application.getSharedPreferences(SHARED_PREFERENCES_MUSIC,Context.MODE_PRIVATE);
    }

    @PerApplication
    @Provides
    @Named(SHARED_PREFERENCES_TIPS)
    SharedPreferences provideSharedPreferencesTips(Application application){
        return application.getSharedPreferences(SHARED_PREFERENCES_TIPS,Context.MODE_PRIVATE);
    }

    @Provides
    @PerApplication
    @Named("music")
    EventBus<Boolean> provideEventBusMusic(){
        return new EventBus<>();
    }

    @Provides
    @PerApplication
    @Named("sound")
    EventBus<Boolean> provideEventBusSound(){
        return new EventBus<>();
    }
}
