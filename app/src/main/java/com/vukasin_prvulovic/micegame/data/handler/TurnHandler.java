package com.vukasin_prvulovic.micegame.data.handler;

import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.MiceMade;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;

public class TurnHandler extends Handler{

    public TurnHandler(GameSnapshotManager manager, Game game, String previousField, String nextField) {
        super(manager, game, previousField, nextField);
    }

    @Override
    public Game handle() {

        if (getGame().getGameState().getMiceMade()== MiceMade.MICA_IS_MADE){
            getManager().addSnapshot(getGame());
            return getGame();
        }

        if (getGame().getGameState().getNextPlayerState()== PlayerState.PLAYER_ONE)
            getGame().getGameState().setNextPlayerState(PlayerState.PLAYER_TWO);
        else
            getGame().getGameState().setNextPlayerState(PlayerState.PLAYER_ONE);

        getGame().setNumberOfPlayers(getGame().getNumberOfPlayers()-1);

        return super.handle();
    }
}
