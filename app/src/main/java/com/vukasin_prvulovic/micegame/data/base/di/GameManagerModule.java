package com.vukasin_prvulovic.micegame.data.base.di;

import com.vukasin_prvulovic.micegame.data.initializers.GameInitializer;
import com.vukasin_prvulovic.micegame.data.local.manager.GameManager;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.repository.base.GameRepository;
import com.vukasin_prvulovic.micegame.data.repository.repositories.GameRepositoryImpl;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
@PerFragment
public class GameManagerModule {

    @PerFragment
    @Provides
    GameManager provideGameManager(GameInitializer gameInitializer, GameSnapshotManager gameSnapshotManager){
        return new GameManager(gameInitializer,gameSnapshotManager);
    }

    @PerFragment
    @Provides
    GameRepository provideGameRepository(GameRepositoryImpl gameRepository){
        return gameRepository;
    }
}
