package com.vukasin_prvulovic.micegame.data.local.manager;

import com.vukasin_prvulovic.micegame.data.handler.EatHandler;
import com.vukasin_prvulovic.micegame.data.handler.MiceHandler;
import com.vukasin_prvulovic.micegame.data.handler.MoveHandler;
import com.vukasin_prvulovic.micegame.data.handler.SetHandler;
import com.vukasin_prvulovic.micegame.data.handler.TurnHandler;
import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.handler.WinnerHandler;
import com.vukasin_prvulovic.micegame.data.initializers.GameInitializer;
import com.vukasin_prvulovic.micegame.data.model.Game;

import io.reactivex.Observable;

public class GameManager {

    private GameSnapshotManager gameSnapshotManager;
    private GameInitializer gameInitializer;

    public GameManager(GameInitializer gameInitializer, GameSnapshotManager gameSnapshotManager) {
        this.gameSnapshotManager=gameSnapshotManager;
        this.gameInitializer=gameInitializer;
    }

    public Observable<Game> initGame(){
        Game game=gameInitializer.initGame();
        gameSnapshotManager.addSnapshot(game);
        return Observable.just(game);
    }

    public Observable<Game> play(Game game,String previousPlayer, String nextPlayer){

        Handler root=new Handler(gameSnapshotManager,game,previousPlayer,nextPlayer);
        root.add(new EatHandler(gameSnapshotManager,game,previousPlayer,nextPlayer));
        root.add(new MoveHandler(gameSnapshotManager,game,previousPlayer,nextPlayer));
        root.add(new MiceHandler(gameSnapshotManager,game,previousPlayer,nextPlayer));
        root.add(new TurnHandler(gameSnapshotManager,game,previousPlayer,nextPlayer));
        root.add(new SetHandler(gameSnapshotManager,game,previousPlayer,nextPlayer));
        root.add(new WinnerHandler(gameSnapshotManager,game,previousPlayer,nextPlayer));

        root.handle();

        return Observable.just(game);
    }

    public Observable<Game> undo(){
        Game game=gameSnapshotManager.undo();
        return Observable.just(game);
    }

    public Observable<Game> getLastGame(){
        Game game=gameSnapshotManager.getLastGameSnapshot();
        if (game==null){
            return initGame();
        }else{
            return Observable.just(game);
        }
    }
}
