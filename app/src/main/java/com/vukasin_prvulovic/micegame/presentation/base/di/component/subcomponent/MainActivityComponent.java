package com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent;

import com.vukasin_prvulovic.micegame.presentation.base.di.module.FragmentBuilder;
import com.vukasin_prvulovic.micegame.presentation.base.di.module.MainActivityModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;
import com.vukasin_prvulovic.micegame.presentation.view.activities.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@PerActivity
@Subcomponent(modules = {FragmentBuilder.class, AndroidSupportInjectionModule.class, MainActivityModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
