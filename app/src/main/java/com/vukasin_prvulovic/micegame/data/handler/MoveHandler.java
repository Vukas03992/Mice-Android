package com.vukasin_prvulovic.micegame.data.handler;

import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.FirstNeighborDeleteHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.SecondNeighborDeleteHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.base.DeleteMiceBaseHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;
import com.vukasin_prvulovic.micegame.data.model.state.SetState;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;

public class MoveHandler extends Handler{

    public MoveHandler(GameSnapshotManager manager, Game game, String previousField, String nextField) {
        super(manager, game, previousField, nextField);
    }

    @Override
    public Game handle() {

        //take reference of current player
        PlayerState playerState = getGame().getGameState().getNextPlayerState();
        FieldState fieldState;

        //convert current player to field state
        if (playerState.getType()==FieldState.PLAYER_ONE.getType())
            fieldState=FieldState.PLAYER_ONE;
        else
            fieldState=FieldState.PLAYER_TWO;

        if (getGame().getGameState().getSetState()== SetState.STILL_SET){

            Field nextField=getGame().getFields().get(getNextField());

            if (nextField.getFieldState()==FieldState.EMPTY_FIELD)
                nextField.setFieldState(fieldState);

        }else{

            Field previousField=getGame().getFields().get(getPreviousField());
            Field nextField=getGame().getFields().get(getNextField());

            //check if pressed field is type of current player
            if (previousField.getFieldState().getType()==fieldState.getType()){

                for (Field miceField : previousField.getNeighbors()) {
                    //check if neighbor of previous field is equals of next field
                    if (miceField.getId().equals(nextField.getId())){

                        if (miceField.getFieldState()==FieldState.EMPTY_FIELD) {
                            if (previousField.getMiceState() == MiceState.IN_MICE) {
                                getGame().getFields().get(getPreviousField()).setMiceState(MiceState.NO_MICE);
                                Mice mice = new Mice();
                                DeleteMiceBaseHandler deleteMiceBaseHandler =
                                        new DeleteMiceBaseHandler(mice, getGame().getFields().get(getPreviousField()),nextField);
                                deleteMiceBaseHandler.add(new FirstNeighborDeleteHandler(mice, getGame().getFields().get(getPreviousField()),nextField));
                                deleteMiceBaseHandler.add(new SecondNeighborDeleteHandler(mice, getGame().getFields().get(getPreviousField()),nextField));
                                deleteMiceBaseHandler.handler();
                            }
                            previousField.setFieldState(FieldState.EMPTY_FIELD);
                            nextField.setFieldState(fieldState);
                        }
                    }
                }
            }
        }
        //check if move is not successful
        if (getGame().getFields().get(getNextField()).getFieldState()!=fieldState){
            return getGame();
        }else if (getGame().getGameState().getSetState()==SetState.SET_OVER && getGame().getFields().get(getPreviousField()).getFieldState()==fieldState){
            return getGame();
        }

        return super.handle();
    }
}
