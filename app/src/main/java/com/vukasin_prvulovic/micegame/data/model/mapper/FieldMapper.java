package com.vukasin_prvulovic.micegame.data.model.mapper;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldMapper {

    @SerializedName("row")
    private int row;
    @SerializedName("col")
    private int col;
    @SerializedName("neighborhood")
    private List<String> neighbors;
    @SerializedName("type")
    private String fieldType;

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

    public List<String> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<String> neighbors) {
        this.neighbors = neighbors;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return "FieldMapper{" +
                "row=" + row +
                ", col=" + col +
                ", neighbors=" + neighbors +
                '}';
    }
}
