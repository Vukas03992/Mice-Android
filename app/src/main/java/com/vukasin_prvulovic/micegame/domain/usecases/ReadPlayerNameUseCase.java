package com.vukasin_prvulovic.micegame.domain.usecases;

import com.vukasin_prvulovic.micegame.data.repository.base.SaveReadPlayerNameRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class ReadPlayerNameUseCase extends UseCase<String[]>{

    private SaveReadPlayerNameRepository repository;

    @Inject
    public ReadPlayerNameUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, SaveReadPlayerNameRepository repository) {
        super(executor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<String[]> buildUseCaseObservable(Object... params) {
        return repository.readPlayersNames();
    }
}
