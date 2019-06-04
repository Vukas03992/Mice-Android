package com.vukasin_prvulovic.micegame.presentation.base.di.component.subcomponent;

import com.vukasin_prvulovic.micegame.data.base.di.GameManagerModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.module.BoardFragmentModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.BoardFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(modules = {BoardFragmentModule.class})
public interface BoardFragmentComponent extends AndroidInjector<BoardFragment>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BoardFragment> {}
}
