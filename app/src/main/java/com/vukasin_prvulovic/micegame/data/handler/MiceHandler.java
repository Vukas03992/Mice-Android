package com.vukasin_prvulovic.micegame.data.handler;

import com.vukasin_prvulovic.micegame.data.handler.base.Handler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.FirstNeighborAddHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.SecondNeighborAddHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.base.AddMiceBaseHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.MiceMade;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;

public class MiceHandler extends Handler{

    public MiceHandler(GameSnapshotManager manager, Game game, String previousField, String nextField) {
        super(manager, game, previousField, nextField);
    }

    @Override
    public Game handle() {

        //block of checking for mice
        Mice mice=new Mice();
        AddMiceBaseHandler addMiceBaseHandler =new AddMiceBaseHandler(mice,getGame().getFields().get(getNextField()),getGame().getGameState().getNextPlayerState());
        addMiceBaseHandler.add(new FirstNeighborAddHandler(mice,getGame().getFields().get(getNextField()),getGame().getGameState().getNextPlayerState()));
        addMiceBaseHandler.add(new SecondNeighborAddHandler(mice,getGame().getFields().get(getNextField()),getGame().getGameState().getNextPlayerState()));
        addMiceBaseHandler.handle();

        //check if mice is made
        if (mice.isHasRowMice()){
            getGame().getFields().get(getNextField()).setMiceState(MiceState.IN_MICE);
            for (Field miceField : getGame().getFields().get(getNextField()).getNeighbors()) {
                if (miceField.getRow()==getGame().getFields().get(getNextField()).getRow()){
                    miceField.setMiceState(MiceState.IN_MICE);
                    for (Field field : miceField.getNeighbors()) {
                        if (field.getRow()==miceField.getRow()){
                            field.setMiceState(MiceState.IN_MICE);
                        }
                    }
                }
            }
            getGame().getGameState().setMiceMade(MiceMade.MICA_IS_MADE);
        }

        if (mice.isHasColMice()){
            getGame().getFields().get(getNextField()).setMiceState(MiceState.IN_MICE);
            for (Field miceField : getGame().getFields().get(getNextField()).getNeighbors()) {
                if (miceField.getCol()==getGame().getFields().get(getNextField()).getCol()){
                    miceField.setMiceState(MiceState.IN_MICE);
                    for (Field field : miceField.getNeighbors()) {
                        if (field.getCol()==miceField.getCol()){
                            field.setMiceState(MiceState.IN_MICE);
                        }
                    }
                }
            }
            getGame().getGameState().setMiceMade(MiceMade.MICA_IS_MADE);
        }

        return super.handle();
    }
}
