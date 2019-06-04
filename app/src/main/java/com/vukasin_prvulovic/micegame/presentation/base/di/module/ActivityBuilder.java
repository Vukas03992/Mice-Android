package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import android.app.Activity;

import com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent.MainActivityComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import com.vukasin_prvulovic.micegame.presentation.view.activities.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@PerApplication
@Module(subcomponents = {MainActivityComponent.class})
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);
}
