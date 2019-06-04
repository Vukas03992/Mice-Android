package com.vukasin_prvulovic.micegame.data.handler.micehandler;

import com.vukasin_prvulovic.micegame.data.handler.micehandler.base.AddMiceBaseHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;

public class SecondNeighborAddHandler extends AddMiceBaseHandler {

    public SecondNeighborAddHandler(Mice mice, Field nextField, PlayerState playerState) {
        super(mice, nextField, playerState);
    }

    @Override
    public void handle() {

        if (getMice().getRow()==null && getMice().getCol()==null){
            return;
        }

        if (getMice().getRow()!=null && !getMice().isHasRowMice()){
            for (Field miceField : getNextField().getNeighbors()) {
                if (miceField.getRow()==getMice().getRow()){
                    for (Field field : miceField.getNeighbors()) {
                        if (field.getRow() == getMice().getRow()
                                && field.getCol() != getNextField().getCol()
                                && field.getFieldState().getType()==getPlayerState().getType()){
                            getMice().setHasRowMice(true);
                            break;
                        }else{
                            getMice().setHasRowMice(false);
                        }
                    }
                }
            }
        }

        if (getMice().getCol()!=null && !getMice().isHasColMice()){
            for (Field miceField : getNextField().getNeighbors()) {
                if (miceField.getCol()==getMice().getCol()){
                    for (Field field : miceField.getNeighbors()) {
                        if (field.getCol() == getMice().getCol()
                                && field.getRow() != getNextField().getRow()
                                && field.getFieldState().getType()==getPlayerState().getType()){
                            getMice().setHasColMice(true);
                            break;
                        }else{
                            getMice().setHasColMice(false);
                        }
                    }
                }
            }
        }

        super.handle();
    }
}
