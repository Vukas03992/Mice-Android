package com.vukasin_prvulovic.micegame.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.presentation.base.view.BaseFragment;
import com.vukasin_prvulovic.micegame.presentation.presenters.MenuFragmentPresenter;
import com.vukasin_prvulovic.micegame.presentation.presenters.Transaction;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.MenuView;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class MenuFragment extends BaseFragment implements MenuView,View.OnClickListener{

    @Inject
    MenuFragmentPresenter presenter;
    @BindView(R.id.menu_fragment_button_new_game)View newGameButton;
    @BindView(R.id.menu_fragment_button_continue_game)View continueGame;
    @BindView(R.id.menu_fragment_button_players)View playerNameButton;
    @BindView(R.id.menu_fragment_button_rules)View rulesButton;
    @BindView(R.id.menu_fragment_button_exit)View exitButton;

    public static MenuFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initView() {
        newGameButton.setOnClickListener(this);
        continueGame.setOnClickListener(this);
        playerNameButton.setOnClickListener(this);
        rulesButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        newGameButton.setOnClickListener(null);
        continueGame.setOnClickListener(null);
        playerNameButton.setOnClickListener(null);
        rulesButton.setOnClickListener(null);
        exitButton.setOnClickListener(null);
    }

    @Override
    public void onClick(View view) {
        presenter.playClickSound();
        switch (view.getId()){
            case R.id.menu_fragment_button_new_game:
                presenter.clearGameHistory();
                break;
            case R.id.menu_fragment_button_continue_game:
                presenter.onClick(Transaction.BOARD_FRAGMENT);
                break;
            case R.id.menu_fragment_button_players:
                presenter.onClick(Transaction.PLAYER_NAME_FRAGMENT);
                break;
            case R.id.menu_fragment_button_rules:
                presenter.onClick(Transaction.RULES_FRAGMENT);
                break;
            case R.id.menu_fragment_button_exit:
                presenter.onClick(Transaction.EXIT);
                break;
        }
    }
}
