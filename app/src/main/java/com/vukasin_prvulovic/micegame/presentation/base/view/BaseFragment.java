package com.vukasin_prvulovic.micegame.presentation.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment{

    private View view;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId=getLayoutId();
        this.inflater=inflater;
        view=inflater.inflate(layoutId,container,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    protected abstract int getLayoutId();
    protected abstract void initView();

    public View getView(){
        return view;
    }
}
