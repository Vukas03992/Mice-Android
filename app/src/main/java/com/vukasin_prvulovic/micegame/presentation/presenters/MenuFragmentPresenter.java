package com.vukasin_prvulovic.micegame.presentation.presenters;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.domain.base.interaction.DefaultObserver;
import com.vukasin_prvulovic.micegame.domain.usecases.InitGameUseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.base.presenter.Presenter;
import com.vukasin_prvulovic.micegame.presentation.view.activities.SoundPlayer;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.MenuView;

import javax.inject.Inject;
import javax.inject.Named;

@PerFragment
public class MenuFragmentPresenter extends Presenter {

    private EventBus<Transaction> eventBus;
    private MenuView view;
    @Inject
    InitGameUseCase initGameUseCase;
    @Inject
    SoundPlayer soundPlayer;

    @Inject
    public MenuFragmentPresenter(@Named("transactions") EventBus<Transaction> eventBus, MenuView view) {
        this.eventBus = eventBus;
        this.view = view;
    }

    public void onClick(Transaction transaction){
        eventBus.send(transaction);
    }

    public void clearGameHistory(){
        initGameUseCase.execute(new InitGameObserver());
    }

    public void playClickSound(){
        soundPlayer.playPutEffect();
    }

    @Override
    public void onStart() {}
    @Override
    public void onStop() {}

    private class InitGameObserver extends DefaultObserver<Boolean>{
        @Override
        public void onNext(Boolean aBoolean) {
            onClick(Transaction.BOARD_FRAGMENT);
        }
    }
}
