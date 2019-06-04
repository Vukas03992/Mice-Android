package com.vukasin_prvulovic.micegame.presentation.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.presentation.base.view.BaseActivity;
import com.vukasin_prvulovic.micegame.presentation.presenters.MainActivityPresenter;
import com.vukasin_prvulovic.micegame.presentation.presenters.Transaction;
import com.vukasin_prvulovic.micegame.presentation.view.activities.abstraction.MainActivityView;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.BoardFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.MenuFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.PlayerNameFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.RulesFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector,MainActivityView{

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    MainActivityPresenter presenter;
    @Inject
    FragmentManager fragmentManager;
    @BindView(R.id.main_toolbar)Toolbar toolbar;

    private BoardFragment boardFragment;
    private RulesFragment rulesFragment;
    private PlayerNameFragment playerNameFragment;
    private MenuFragment menuFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        toolbar.setLogo(R.drawable.logo_actionbar);
        setSupportActionBar(toolbar);
        initFragments();
        presenter.onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.playMusic();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void initMenu() {
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container,menuFragment)
                .commit();
    }

    @Override
    public void doTransaction(Transaction transaction) {
        switch (transaction){
            case BOARD_FRAGMENT:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,boardFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case PLAYER_NAME_FRAGMENT:
                if (playerNameFragment.isAdded()){
                    hideKeyboard();
                    onBackPressed();}
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,playerNameFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case RULES_FRAGMENT:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,rulesFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case EXIT:
                this.finish();
        }
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }

    private void initFragments(){
        menuFragment=MenuFragment.newInstance();
        boardFragment=BoardFragment.newInstance();
        playerNameFragment=PlayerNameFragment.newInstance();
        rulesFragment=RulesFragment.newInstance();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
