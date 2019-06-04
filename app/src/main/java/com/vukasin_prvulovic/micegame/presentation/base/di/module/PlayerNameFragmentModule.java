package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.PlayerNameFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.PlayerNameView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PlayerNameFragmentModule {

    @Provides
    @PerFragment
    PlayerNameView providePlayerNameView(PlayerNameFragment playerNameFragment){
        return playerNameFragment;
    }
}
