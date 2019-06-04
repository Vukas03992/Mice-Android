package com.vukasin_prvulovic.micegame.domain.usecases;

import com.vukasin_prvulovic.micegame.data.repository.base.TipsRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class GetTipsPermissionUseCase extends UseCase<Boolean> {

    private TipsRepository repository;

    @Inject
    public GetTipsPermissionUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, TipsRepository repository) {
        super(executor, postExecutionThread);
        this.repository=repository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Object... params) {
        return repository.getTipsPermission();
    }
}
