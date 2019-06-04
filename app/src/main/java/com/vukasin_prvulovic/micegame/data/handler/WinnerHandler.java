package com.vukasin_prvulovic.micegame.data.handler;

import android.util.Log;

import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.WinnerByMoveHandler;
import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.WinnerByNumberHandler;
import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.base.WinnerBaseHandler;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.SetState;

import static android.content.ContentValues.TAG;

public class WinnerHandler extends Handler{

    public WinnerHandler(GameSnapshotManager manager, Game game, String previousField, String nextField) {
        super(manager, game, previousField, nextField);
    }

    @Override
    public Game handle() {

        if (getGame().getGameState().getSetState()== SetState.SET_OVER) {

            WinnerBaseHandler handler = new WinnerBaseHandler(getGame());
            handler.add(new WinnerByNumberHandler(getGame()));
            handler.add(new WinnerByMoveHandler(getGame()));

            handler.handle();
        }
        getManager().addSnapshot(getGame());

        return super.handle();
    }
}
