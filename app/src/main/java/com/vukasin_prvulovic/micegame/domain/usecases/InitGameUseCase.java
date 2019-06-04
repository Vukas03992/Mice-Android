package com.vukasin_prvulovic.micegame.domain.usecases;

import com.vukasin_prvulovic.micegame.data.repository.base.InitGameRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class InitGameUseCase extends UseCase<Boolean> {

    private InitGameRepository repository;

    @Inject
    public InitGameUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, InitGameRepository repository) {
        super(executor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Object... params) {
        return repository.clearGameHistory();
    }
}
