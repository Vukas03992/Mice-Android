package com.vukasin_prvulovic.micegame.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.presentation.base.view.BaseFragment;
import com.vukasin_prvulovic.micegame.presentation.presenters.PlayerNameFragmentPresenter;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.PlayerNameView;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class PlayerNameFragment extends BaseFragment implements PlayerNameView,TextView.OnEditorActionListener{

    @Inject
    PlayerNameFragmentPresenter presenter;
    @BindView(R.id.name_fragment_edit_text_player_one)EditText playerOneEditText;
    @BindView(R.id.names_edit_text_player_two)EditText playerTwoEditText;

    public static PlayerNameFragment newInstance() {

        Bundle args = new Bundle();

        PlayerNameFragment fragment = new PlayerNameFragment();
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
        return R.layout.fragment_name;
    }

    @Override
    protected void initView() {
        playerTwoEditText.setOnEditorActionListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onStop();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i== EditorInfo.IME_ACTION_DONE){
            presenter.writePlayersNames(playerOneEditText.getText().toString(),
                                        playerTwoEditText.getText().toString());
            return true;
        }
        return false;
    }
}
