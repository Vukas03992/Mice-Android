package com.vukasin_prvulovic.micegame.data.handler.winnerhandler;

import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.base.WinnerBaseHandler;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;
import com.vukasin_prvulovic.micegame.data.model.state.WinnerState;

public class WinnerByMoveHandler extends WinnerBaseHandler{

    public WinnerByMoveHandler(Game game) {
        super(game);
    }

    @Override
    public void handle() {

        boolean canNotMove=true;

        if (getGame().getGameState().getNextPlayerState()== PlayerState.PLAYER_ONE){
            for (Field field : getGame().getFields().values()) {
                if (field.getFieldState()== FieldState.PLAYER_ONE){
                    for (Field field1 : field.getNeighbors()) {
                        if (field1.getFieldState()==FieldState.EMPTY_FIELD){
                            canNotMove=false;
                            break;
                        }
                    }
                }
            }
        }else{
            for (Field field : getGame().getFields().values()) {
                if (field.getFieldState()== FieldState.PLAYER_TWO){
                    for (Field field1 : field.getNeighbors()) {
                        if (field1.getFieldState()==FieldState.EMPTY_FIELD){
                            canNotMove=false;
                            break;
                        }
                    }
                }
            }
        }

        if (canNotMove){
            if (getGame().getGameState().getNextPlayerState()== PlayerState.PLAYER_ONE){
                getGame().getGameState().setWinnerState(WinnerState.WINNER_TWO);
            }else{
                getGame().getGameState().setWinnerState(WinnerState.WINNER_ONE);
            }
        }

        super.handle();

    }
}
