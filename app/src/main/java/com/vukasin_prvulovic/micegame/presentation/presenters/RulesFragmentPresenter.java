package com.vukasin_prvulovic.micegame.presentation.presenters;

import android.util.Log;

import com.vukasin_prvulovic.micegame.domain.base.interaction.DefaultObserver;
import com.vukasin_prvulovic.micegame.domain.usecases.GetTipsPermissionUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.SaveTipsPermissionUseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.base.presenter.Presenter;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.RulesView;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

@PerFragment
public class RulesFragmentPresenter extends Presenter{

    private RulesView view;
    @Inject
    SaveTipsPermissionUseCase saveTipsPermissionUseCase;
    @Inject
    GetTipsPermissionUseCase getTipsPermissionUseCase;

    @Inject
    public RulesFragmentPresenter(RulesView view) {
        this.view = view;
    }

    public void check(boolean checked){
        saveTipsPermissionUseCase.execute(checked);
    }

    @Override
    public void onStart() {
        getTipsPermissionUseCase.execute(new TipsObserver());
    }

    @Override
    public void onStop() {}

    private class TipsObserver extends DefaultObserver<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            view.setCheckState(aBoolean);
        }
    }
}
