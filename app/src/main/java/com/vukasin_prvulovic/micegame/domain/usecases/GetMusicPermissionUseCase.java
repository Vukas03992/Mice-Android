package com.vukasin_prvulovic.micegame.domain.usecases;

import com.vukasin_prvulovic.micegame.data.repository.base.MusicRepository;
import com.vukasin_prvulovic.micegame.domain.base.executor.PostExecutionThread;
import com.vukasin_prvulovic.micegame.domain.base.executor.ThreadExecutor;
import com.vukasin_prvulovic.micegame.domain.base.interaction.UseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerActivity;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerActivity
public class GetMusicPermissionUseCase extends UseCase<Boolean>{

    private MusicRepository repository;

    @Inject
    public GetMusicPermissionUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, MusicRepository repository) {
        super(executor, postExecutionThread);
        this.repository=repository;
    }


    @Override
    protected Observable<Boolean> buildUseCaseObservable(Object... params) {
        return repository.getMusicPermission();
    }
}
