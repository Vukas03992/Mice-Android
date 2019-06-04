package com.vukasin_prvulovic.micegame.data.handler;

import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.SetState;

public class SetHandler extends Handler{

    public SetHandler(GameSnapshotManager manager, Game game, String previousField, String nextField) {
        super(manager, game, previousField, nextField);
    }

    @Override
    public Game handle() {

        if (getGame().getNumberOfPlayers()<=0){
            getGame().getGameState().setSetState(SetState.SET_OVER);
        }
        return super.handle();
    }
}
