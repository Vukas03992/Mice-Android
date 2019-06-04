package com.vukasin_prvulovic.micegame.data.handler.micehandler;


import com.vukasin_prvulovic.micegame.data.handler.micehandler.base.DeleteMiceBaseHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;

public class FirstNeighborDeleteHandler extends DeleteMiceBaseHandler{

    public FirstNeighborDeleteHandler(Mice mice, Field previousField,Field nextField) {
        super(mice, previousField, nextField);
    }

    @Override
    public void handler() {

        switch (getPreviousField().getFieldType()){
            case PRIMARY: check();
                break;
            case SECONDARY: check();
                break;
            case TERNARY: check();
                return;
        }

        super.handler();
    }

    private void check(){

        for (Field firstNeighbor : getPreviousField().getNeighbors()) {
            if (firstNeighbor.getMiceState() == MiceState.IN_MICE && firstNeighbor.getFieldState() == getPreviousField().getFieldState()) {
                if (firstNeighbor.getRow() == getPreviousField().getRow() && firstNeighbor.getRow()!=getNextField().getRow()) {
                    getMice().setHasRowMice(true);
                    getMice().setHasColMice(false);
                    checkNeighbor(firstNeighbor);
                }
                if (firstNeighbor.getCol() == getPreviousField().getCol() && firstNeighbor.getCol()!=getNextField().getCol()) {
                    getMice().setHasColMice(true);
                    getMice().setHasRowMice(false);
                    checkNeighbor(firstNeighbor);
                }
            }
        }
    }


    private void checkNeighbor(Field firstNeighbor){
        if (getMice().isHasRowMice()){
            for (Field field : firstNeighbor.getNeighbors()) {
                if (field.getRow()!=getPreviousField().getRow() && field.getCol()==firstNeighbor.getCol()){
                    if (field.getMiceState()==MiceState.NO_MICE
                            || (field.getMiceState()==MiceState.IN_MICE && field.getFieldState()!=getPreviousField().getFieldState())){
                        firstNeighbor.setMiceState(MiceState.NO_MICE);
                    }
                }
            }
        }else{
            for (Field field : firstNeighbor.getNeighbors()) {
                if (field.getCol()!=getPreviousField().getCol() && field.getRow()==firstNeighbor.getRow()){
                    if (field.getMiceState()==MiceState.NO_MICE
                            || (field.getMiceState()==MiceState.IN_MICE && field.getFieldState()!=getPreviousField().getFieldState())){
                        firstNeighbor.setMiceState(MiceState.NO_MICE);
                    }
                }
            }
        }
    }

}
