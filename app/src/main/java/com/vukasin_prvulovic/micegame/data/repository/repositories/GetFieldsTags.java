package com.vukasin_prvulovic.micegame.data.repository.repositories;

import android.content.res.AssetManager;

import com.vukasin_prvulovic.micegame.data.local.manager.FieldTagManager;
import com.vukasin_prvulovic.micegame.data.repository.base.FieldsTagRepository;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerApplication
public class GetFieldsTags implements FieldsTagRepository{

    private FieldTagManager manager;

    @Inject
    public GetFieldsTags(FieldTagManager manager) {
        this.manager = manager;
    }

    @Override
    public Observable<List<String>> getFieldsTags() {
        return manager.readFieldsTags();
    }
}
