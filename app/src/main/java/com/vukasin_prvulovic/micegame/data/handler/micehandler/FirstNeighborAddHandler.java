package com.vukasin_prvulovic.micegame.data.handler.micehandler;

import com.vukasin_prvulovic.micegame.data.handler.micehandler.base.AddMiceBaseHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;

public class FirstNeighborAddHandler extends AddMiceBaseHandler {

    public FirstNeighborAddHandler(Mice mice, Field nextField, PlayerState playerState) {
        super(mice, nextField, playerState);
    }

    @Override
    public void handle() {

        switch (getNextField().getFieldType()){
            case PRIMARY: checkForPrimary();
                break;
            case SECONDARY: checkForSecondary();
                break;
            case TERNARY: checkForTernary();
                return;
        }

        super.handle();
    }

    private void checkForPrimary(){
        for (Field miceField : getNextField().getNeighbors()) {
            if (miceField.getRow()==getNextField().getRow()
                    && miceField.getFieldState().getType()==getPlayerState().getType()){
                getMice().setRow(getNextField().getRow());
            }
            if (miceField.getCol()==getNextField().getCol()
                    && miceField.getFieldState().getType()==getPlayerState().getType()){
                getMice().setCol(getNextField().getCol());
            }
        }
    }

    private void checkForSecondary(){
        if (checkForRowNeighbors()==2){

            if (checkForRowNeighborsForMice()==2){
                getMice().setRow(getNextField().getRow());
                getMice().setHasRowMice(true);
            }

            for (Field miceField : getNextField().getNeighbors()) {
                if (miceField.getCol()==getNextField().getCol()
                        && miceField.getFieldState().getType()==getPlayerState().getType()){
                    getMice().setCol(getNextField().getCol());
                }
            }

        }else{

            if (checkForColNeighborsForMice()==2){
                getMice().setCol(getNextField().getCol());
                getMice().setHasColMice(true);
            }

            for (Field miceField : getNextField().getNeighbors()) {
                if (miceField.getRow()==getNextField().getRow()
                        && miceField.getFieldState().getType()==getPlayerState().getType()){
                    getMice().setRow(getNextField().getRow());
                }
            }
        }
    }

    private void checkForTernary(){
        int numberRow=0;
        int numberCol=0;

        for (Field miceField : getNextField().getNeighbors()) {
            if (miceField.getRow()==getNextField().getRow()
                    && miceField.getFieldState().getType()==getPlayerState().getType()){
                numberRow++;
            }
            if (miceField.getCol()==getNextField().getCol()
                    && miceField.getFieldState().getType()==getPlayerState().getType()){
                numberCol++;
            }
        }

        if (numberRow==2){
            getMice().setRow(getNextField().getRow());
            getMice().setHasRowMice(true);
        }
        if (numberCol==2){
            getMice().setCol(getNextField().getCol());
            getMice().setHasColMice(true);
        }
    }

    private int checkForRowNeighbors(){
        int numberOfRowNeighbors=0;
        for (Field miceField : getNextField().getNeighbors()) {
            if (miceField.getRow()==getNextField().getRow()){
                numberOfRowNeighbors++;
            }
        }
        return numberOfRowNeighbors;
    }

    private int checkForRowNeighborsForMice(){
        int number=0;
        for (Field miceField : getNextField().getNeighbors()) {
            if (miceField.getRow()==getNextField().getRow()
                    && miceField.getFieldState().getType()==getPlayerState().getType()){
                number++;
            }
        }
        return number;
    }

    private int checkForColNeighborsForMice(){
        int number=0;
        for (Field miceField : getNextField().getNeighbors()) {
            if (miceField.getCol()==getNextField().getCol()
                    && miceField.getFieldState().getType()==getPlayerState().getType()){
                number++;
            }
        }
        return number;
    }
}
