package com.vukasin_prvulovic.micegame.data.local.manager;

import android.util.Log;

import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.utils.GameUtils;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static android.content.ContentValues.TAG;

@PerApplication
public class GameSnapshotManager {

    private List<Game> gameSnapshots;

    @Inject
    public GameSnapshotManager() {
        gameSnapshots=new ArrayList<>();
    }

    public void addSnapshot(Game game){
        gameSnapshots.add(GameUtils.copyGame(game));
    }

    public Game undo(){

        if (gameSnapshots.size()==1){
            return GameUtils.copyGame(gameSnapshots.get(0));
        }else if (gameSnapshots.size()==2){
            gameSnapshots.remove(gameSnapshots.get(1));
            return GameUtils.copyGame(gameSnapshots.get(0));
        }else{
            gameSnapshots.remove(gameSnapshots.size()-1);
            return gameSnapshots.get(gameSnapshots.size()-1);
        }
    }

    public Observable<Boolean> clearGameSnapshots(){
        gameSnapshots.clear();
        return Observable.just(true);
    }

    public Game getLastGameSnapshot(){
        if (!gameSnapshots.isEmpty()){
            return gameSnapshots.get(gameSnapshots.size()-1);
        }else{
            return null;
        }
    }
}
