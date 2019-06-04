package com.vukasin_prvulovic.micegame.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.presentation.base.view.BaseFragment;
import com.vukasin_prvulovic.micegame.presentation.presenters.RulesFragmentPresenter;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.RulesView;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class RulesFragment extends BaseFragment implements RulesView,CompoundButton.OnCheckedChangeListener{

    @Inject
    RulesFragmentPresenter presenter;

    @BindView(R.id.rules_fragment_check_box)CheckBox checkBox;

    public static RulesFragment newInstance() {

        Bundle args = new Bundle();

        RulesFragment fragment = new RulesFragment();
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
        return R.layout.fragment_rules;
    }

    @Override
    protected void initView() {
        presenter.onStart();
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onStop();
        checkBox.setOnCheckedChangeListener(null);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        presenter.check(b);
    }

    @Override
    public void setCheckState(boolean check) {
        checkBox.setChecked(check);
    }
}
