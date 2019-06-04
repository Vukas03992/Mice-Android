package com.vukasin_prvulovic.micegame.data.handler.micehandler;

import android.util.Log;

import com.vukasin_prvulovic.micegame.data.handler.micehandler.base.DeleteMiceBaseHandler;
import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;

import static android.content.ContentValues.TAG;

public class SecondNeighborDeleteHandler extends DeleteMiceBaseHandler{

    public SecondNeighborDeleteHandler(Mice mice, Field previousField,Field nextField) {
        super(mice, previousField, nextField);
    }

    @Override
    public void handler() {

        if (getMice().isHasRowMice()){
            for (Field miceField : getPreviousField().getNeighbors()) {
                if (miceField.getRow()==getPreviousField().getRow()){
                    for (Field field : miceField.getNeighbors()) {
                        if (field.getCol()!=getPreviousField().getCol() && field.getRow()==getPreviousField().getRow()) {
                            for (Field miceField1 : field.getNeighbors()) {
                                if (miceField1.getRow() != getPreviousField().getRow()
                                        && (miceField1.getMiceState()==MiceState.NO_MICE
                                        || (miceField1.getMiceState()==MiceState.IN_MICE && miceField1.getFieldState()!=field.getFieldState()))) {
                                    field.setMiceState(MiceState.NO_MICE);
                                }
                                if (miceField1.getRow() != getPreviousField().getRow() && miceField1.getMiceState()==MiceState.IN_MICE && miceField1.getFieldState()==getPreviousField().getFieldState()){
                                    for (Field field1 : miceField1.getNeighbors()) {
                                        if (field1.getCol()==field.getCol() && (field1.getMiceState()==MiceState.NO_MICE
                                                || (field1.getMiceState()==MiceState.IN_MICE && field1.getFieldState()!=field.getFieldState()))){
                                            field.setMiceState(MiceState.NO_MICE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (getMice().isHasColMice()){
            for (Field miceField : getPreviousField().getNeighbors()) {
                if (miceField.getCol()==getPreviousField().getCol()){
                    for (Field field : miceField.getNeighbors()) {
                        if (field.getRow()!=getPreviousField().getRow() && field.getCol()==getPreviousField().getCol()) {
                            for (Field miceField1 : field.getNeighbors()) {
                                if (miceField1.getCol() != getPreviousField().getCol()
                                        && (miceField1.getMiceState()==MiceState.NO_MICE
                                        || (miceField1.getMiceState()==MiceState.IN_MICE && miceField1.getFieldState()!=getPreviousField().getFieldState()))) {
                                    field.setMiceState(MiceState.NO_MICE);
                                }
                                if (miceField1.getCol() != getPreviousField().getCol() && miceField1.getMiceState()==MiceState.IN_MICE && miceField1.getFieldState()==getPreviousField().getFieldState()){
                                    for (Field field1 : miceField1.getNeighbors()) {
                                        if (field1.getRow()==field.getRow() && (field1.getMiceState()==MiceState.NO_MICE
                                                || (field1.getMiceState()==MiceState.IN_MICE && field1.getFieldState()!=field.getFieldState()))){
                                            field.setMiceState(MiceState.NO_MICE);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        super.handler();
    }
}
