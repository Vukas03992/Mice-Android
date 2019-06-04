package com.vukasin_prvulovic.micegame.data.handler.winnerhandler;

import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.base.WinnerBaseHandler;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;
import com.vukasin_prvulovic.micegame.data.model.state.WinnerState;

public class WinnerByNumberHandler extends WinnerBaseHandler{

    public WinnerByNumberHandler(Game game) {
        super(game);
    }

    @Override
    public void handle() {

        //note: before calling this method, opponent is already set to be next
        if (getGame().getGameState().getNextPlayerState()== PlayerState.PLAYER_ONE){
            int numberOfPlayerOne=getNumberOfPlayers(FieldState.PLAYER_ONE);
            if (numberOfPlayerOne<=2){
                getGame().getGameState().setWinnerState(WinnerState.WINNER_TWO);
                return;
            }
        }else{
            int numberOfPlayerTwo=getNumberOfPlayers(FieldState.PLAYER_TWO);
            if (numberOfPlayerTwo<=2){
                getGame().getGameState().setWinnerState(WinnerState.WINNER_ONE);
                return;
            }
        }

        super.handle();
    }

    private int getNumberOfPlayers(FieldState fieldState){
        int number=0;
        for (Field field : getGame().getFields().values()) {
            if (field.getFieldState()==fieldState){
                number=number+1;
            }
        }
        return number;
    }
}
