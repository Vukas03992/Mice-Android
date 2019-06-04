package com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent;

import com.vukasin_prvulovic.micegame.presentation.base.di.module.RulesFragmentModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.RulesFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(modules = RulesFragmentModule.class)
public interface RulesFragmentComponent extends AndroidInjector<RulesFragment>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RulesFragment>{}
}
