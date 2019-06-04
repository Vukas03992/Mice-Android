package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import android.support.v4.app.Fragment;

import com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent.BoardFragmentComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent.MenuFragmentComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent.PlayerNameFragmentComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent.RulesFragmentComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.BoardFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.MenuFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.PlayerNameFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.RulesFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@PerActivity
@Module(subcomponents = {MenuFragmentComponent.class,PlayerNameFragmentComponent.class, RulesFragmentComponent.class,BoardFragmentComponent.class})
public abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(MenuFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMenuFragment(MenuFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(PlayerNameFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindPlayerNameFragment(PlayerNameFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RulesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindRulesFragment(RulesFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BoardFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindBoardFragment(BoardFragmentComponent.Builder builder);

}
