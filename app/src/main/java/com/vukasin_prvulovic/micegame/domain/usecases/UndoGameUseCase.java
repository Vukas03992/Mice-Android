package com.vukasin_prvulovic.micegame.domain.usecases;

import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.repository.base.GameRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class UndoGameUseCase extends UseCase<Game>{

    @Inject
    GameRepository gameRepository;

    @Inject
    public UndoGameUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread) {
        super(executor, postExecutionThread);
    }

    @Override
    protected Observable<Game> buildUseCaseObservable(Object... params) {
        return gameRepository.undo();
    }
}
