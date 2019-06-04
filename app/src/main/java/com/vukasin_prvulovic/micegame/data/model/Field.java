package com.vukasin_prvulovic.micegame.data.model;

import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.MiceState;

import java.util.List;

public class Field {

    private String id;
    private int row;
    private int col;
    private FieldState fieldState=FieldState.EMPTY_FIELD;
    private MiceState miceState=MiceState.NO_MICE;
    private List<Field> neighbors;
    private FieldType fieldType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    public MiceState getMiceState() {
        return miceState;
    }

    public void setMiceState(MiceState miceState) {
        this.miceState = miceState;
    }

    public List<Field> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Field> neighbors) {
        this.neighbors = neighbors;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}
