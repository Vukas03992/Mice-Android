package com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent;

import com.vukasin_prvulovic.micegame.presentation.base.di.module.MenuFragmentModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.MenuFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(modules = {MenuFragmentModule.class})
public interface MenuFragmentComponent extends AndroidInjector<MenuFragment>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MenuFragment>{}
}
