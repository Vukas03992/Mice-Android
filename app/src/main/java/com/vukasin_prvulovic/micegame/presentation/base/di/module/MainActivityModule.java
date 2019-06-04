package com.vukasin_prvulovic.micegame.presentation.base.di.module;

import android.support.v4.app.FragmentManager;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;
import com.vukasin_prvulovic.micegame.presentation.presenters.Transaction;
import com.vukasin_prvulovic.micegame.presentation.view.activities.MainActivity;
import com.vukasin_prvulovic.micegame.presentation.view.activities.abstraction.MainActivityView;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.BoardFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.MenuFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.PlayerNameFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.RulesFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainActivityModule {

    @Provides
    @PerActivity
    MainActivityView provideMainActivityView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    @PerActivity
    FragmentManager provideFragmentManager(MainActivity mainActivity){
        return mainActivity.getSupportFragmentManager();
    }

    @Provides
    @PerActivity
    @Named("transactions")
    EventBus<Transaction> provideEventBusTransition(){
        return new EventBus<>();
    }

    @Provides
    @PerActivity
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    @Named("exit")
    EventBus<Boolean> provideEventBusExit(){
        return new EventBus<>();
    }
}
