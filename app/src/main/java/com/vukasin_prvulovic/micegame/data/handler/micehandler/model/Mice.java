package com.vukasin_prvulovic.micegame.data.handler.micehandler.model;

public class Mice {

    private Integer row;
    private Integer col;
    private boolean hasRowMice=false;
    private boolean hasColMice=false;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public boolean isHasRowMice() {
        return hasRowMice;
    }

    public void setHasRowMice(boolean hasRowMice) {
        this.hasRowMice = hasRowMice;
    }

    public boolean isHasColMice() {
        return hasColMice;
    }

    public void setHasColMice(boolean hasColMice) {
        this.hasColMice = hasColMice;
    }

    @Override
    public String toString() {
        return "Mice{" +
                "row=" + row +
                ", col=" + col +
                ", hasRowMice=" + hasRowMice +
                ", hasColMice=" + hasColMice +
                '}';
    }
}
