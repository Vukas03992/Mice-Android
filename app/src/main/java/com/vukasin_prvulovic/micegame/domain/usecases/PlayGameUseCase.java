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
public class PlayGameUseCase extends UseCase<Game>{

    @Inject
    GameRepository gameRepository;

    @Inject
    public PlayGameUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread) {
        super(executor, postExecutionThread);
    }

    @Override
    protected Observable<Game> buildUseCaseObservable(Object... params) {
        Game game=(Game)  params[0];
        String previousFields=(String)params[1];
        String nextField=(String) params[2];
        return gameRepository.play(game,previousFields,nextField);
    }
}
