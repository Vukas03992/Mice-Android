package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import com.vukasin_prvulovic.micegame.data.base.di.GameManagerModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.BoardFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.BoardView;

import dagger.Module;
import dagger.Provides;

@PerFragment
@Module(includes = {GameManagerModule.class})
public class BoardFragmentModule {

    @Provides
    @PerFragment
    BoardView provideBoardView(BoardFragment boardFragment){
        return boardFragment;
    }
}
