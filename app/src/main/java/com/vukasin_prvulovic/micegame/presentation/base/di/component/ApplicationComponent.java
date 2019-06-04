package com.vukasin_prvulovic.micegame.presentation.base.di.component;

import com.vukasin_prvulovic.micegame.data.base.di.DataModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.module.ActivityBuilder;
import com.vukasin_prvulovic.micegame.presentation.base.di.module.ApplicationModule;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import com.vukasin_prvulovic.micegame.presentation.base.view.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@PerApplication
@Component(modules = {ApplicationModule.class, AndroidInjectionModule.class, ActivityBuilder.class, DataModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(Application application);
}
