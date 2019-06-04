package com.vukasin_prvulovic.micegame.data.handler;

import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.WinnerByMoveHandler;
import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.WinnerByNumberHandler;
import com.vukasin_prvulovic.micegame.data.handler.winnerhandler.base.WinnerBaseHandler;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.MiceMade;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;
import com.vukasin_prvulovic.micegame.data.model.state.SetState;

public class EatHandler extends Handler{

    public EatHandler(GameSnapshotManager manager, Game game, String previousField, String nextField) {
        super(manager, game, previousField, nextField);
    }

    @Override
    public Game handle() {

        //next field is null -> discard move
        if (getNextField()==null){
            return getGame();
        }

        //check if mice is made in previous move
        if (getGame().getGameState().getMiceMade()== MiceMade.MICA_IS_MADE){

            //check if player pressed and released the same field
            if (getPreviousField().equals(getNextField())){

                //take reference of pressed field
                FieldState fieldState=getGame().getFields().get(getNextField()).getFieldState();
                //take who is on turn
                PlayerState playerPlayerState =getGame().getGameState().getNextPlayerState();

                if (fieldState.getType()!=FieldState.EMPTY_FIELD.getType() &&
                        fieldState.getType()!= playerPlayerState.getType()){

                    if (getGame().getFields().get(getNextField()).getMiceState()== MiceState.NO_MICE){

                        //eat opponent
                        getGame().getFields().get(getNextField()).setFieldState(FieldState.EMPTY_FIELD);
                        //set game to "mice is not made"
                        getGame().getGameState().setMiceMade(MiceMade.MICA_IS_NOT_MADE);
                        //set opponent's turn
                        if (playerPlayerState==PlayerState.PLAYER_ONE){
                            getGame().getGameState().setNextPlayerState(PlayerState.PLAYER_TWO);
                        }else{
                            getGame().getGameState().setNextPlayerState(PlayerState.PLAYER_ONE);
                        }
                        getGame().setNumberOfPlayers(getGame().getNumberOfPlayers()-1);
                        if (getGame().getNumberOfPlayers()<=0){
                            getGame().getGameState().setSetState(SetState.SET_OVER);
                        }

                        //if set is over, check the winner
                        if (getGame().getGameState().getSetState()== SetState.SET_OVER) {

                            WinnerBaseHandler handler = new WinnerBaseHandler(getGame());
                            handler.add(new WinnerByNumberHandler(getGame()));
                            handler.add(new WinnerByMoveHandler(getGame()));

                            handler.handle();
                        }

                        //add to snapshots
                        getManager().addSnapshot(getGame());
                    }
                }
            }

            return getGame();
        }else{
            //check if the same field is pressed and released
            //this is not possible in state "Mica is not made"
            if (getPreviousField().equals(getNextField())){
                return getGame();
            }
            return super.handle();
        }
    }
}
