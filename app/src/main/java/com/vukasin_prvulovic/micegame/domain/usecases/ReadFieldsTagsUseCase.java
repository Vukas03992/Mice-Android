package com.vukasin_prvulovic.micegame.domain.usecases;

import android.util.Log;

import com.vukasin_prvulovic.micegame.data.repository.base.FieldsTagRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class ReadFieldsTagsUseCase extends UseCase<List<String>>{

    private FieldsTagRepository repository;

    @Inject
    public ReadFieldsTagsUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, FieldsTagRepository repository) {
        super(executor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<String>> buildUseCaseObservable(Object... params) {
        return repository.getFieldsTags();
    }
}
