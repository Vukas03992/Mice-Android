package com.vukasin_prvulovic.micegame.domain.usecases;

import com.vukasin_prvulovic.micegame.data.repository.base.SaveReadPlayerNameRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerFragment
public class SavePlayerNameUseCase extends UseCase<Boolean> {

    private SaveReadPlayerNameRepository readPlayerNameRepository;

    @Inject
    public SavePlayerNameUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, SaveReadPlayerNameRepository readPlayerNameRepository) {
        super(executor, postExecutionThread);
        this.readPlayerNameRepository = readPlayerNameRepository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Object... params) {
        String playerOne=(String)params[0];
        String playerTwo=(String)params[1];
        return readPlayerNameRepository.savePlayersNames(playerOne,playerTwo);
    }

}
