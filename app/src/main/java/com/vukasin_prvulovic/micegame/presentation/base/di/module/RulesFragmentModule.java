package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.RulesFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.RulesView;

import dagger.Module;
import dagger.Provides;

@Module
public class RulesFragmentModule {

    @PerFragment
    @Provides
    RulesView provideRulesView(RulesFragment rulesFragment){
        return rulesFragment;
    }
}
