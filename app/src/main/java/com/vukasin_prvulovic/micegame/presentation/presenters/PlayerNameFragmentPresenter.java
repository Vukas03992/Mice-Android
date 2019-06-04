package com.vukasin_prvulovic.micegame.presentation.presenters;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.domain.base.interaction.DefaultObserver;
import com.vukasin_prvulovic.micegame.domain.usecases.SavePlayerNameUseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.base.presenter.Presenter;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.PlayerNameView;

import javax.inject.Inject;
import javax.inject.Named;

@PerFragment
public class PlayerNameFragmentPresenter extends Presenter{

    private EventBus<Transaction> eventBus;
    private PlayerNameView view;
    private SavePlayerNameUseCase savePlayerNameUseCase;

    @Inject
    public PlayerNameFragmentPresenter(@Named("transactions") EventBus<Transaction> eventBus,
                                       PlayerNameView view,
                                       SavePlayerNameUseCase savePlayerNameUseCase) {
        this.eventBus = eventBus;
        this.view = view;
        this.savePlayerNameUseCase=savePlayerNameUseCase;
    }

    @Override
    public void onStart() {}

    public void writePlayersNames(String playerOne, String playerTwo){
        savePlayerNameUseCase.execute(new SavePlayerNameObserver(),playerOne,playerTwo);
    }

    @Override
    public void onStop() {
        savePlayerNameUseCase.dispose();
    }

    private class SavePlayerNameObserver extends DefaultObserver<Boolean>{
        @Override
        public void onNext(Boolean aBoolean) {
            if (aBoolean)eventBus.send(Transaction.PLAYER_NAME_FRAGMENT);
        }
    }
}
