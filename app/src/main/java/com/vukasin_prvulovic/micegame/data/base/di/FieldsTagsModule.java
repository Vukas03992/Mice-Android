package com.vukasin_prvulovic.micegame.data.base.di;

import com.vukasin_prvulovic.micegame.data.repository.base.FieldsTagRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.GetFieldsTags;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

@PerApplication
@Module
public class FieldsTagsModule {

    @Provides
    @PerApplication
    FieldsTagRepository provideFieldsTagRepository(GetFieldsTags getFieldsTags){
        return getFieldsTags;
    }
}
