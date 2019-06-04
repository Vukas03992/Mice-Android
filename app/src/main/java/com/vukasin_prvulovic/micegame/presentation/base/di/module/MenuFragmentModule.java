package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import com.vukasin_prvulovic.micegame.data.base.di.InitGameModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.MenuFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.MenuView;

import dagger.Module;
import dagger.Provides;

@PerFragment
@Module(includes = InitGameModule.class)
public class MenuFragmentModule {

    @Provides
    @PerFragment
    MenuView provideMenuFragmentView(MenuFragment menuFragment){
        return menuFragment;
    }
}
