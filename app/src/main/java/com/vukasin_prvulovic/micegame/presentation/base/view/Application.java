package com.vukasin_prvulovic.micegame.presentation.base.view;

import android.app.Activity;

import com.vukasin_prvulovic.micegame.presentation.base.di.component.ApplicationComponent;
import com.vukasin_prvulovic.micegame.presentation.base.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class Application extends android.app.Application implements HasActivityInjector{

    private ApplicationComponent applicationComponent;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent=initComponent();
        applicationComponent.inject(this);
    }

    private ApplicationComponent initComponent(){
        return DaggerApplicationComponent.builder()
                .application(this)
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
