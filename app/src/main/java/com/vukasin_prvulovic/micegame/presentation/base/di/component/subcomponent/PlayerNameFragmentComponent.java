package com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent;

import com.vukasin_prvulovic.micegame.presentation.base.di.module.PlayerNameFragmentModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.PlayerNameFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(modules = {PlayerNameFragmentModule.class})
public interface PlayerNameFragmentComponent extends AndroidInjector<PlayerNameFragment>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PlayerNameFragment>{}
}
