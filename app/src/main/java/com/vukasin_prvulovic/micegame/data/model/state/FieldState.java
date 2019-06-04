package com.vukasin_prvulovic.micegame.data.model.state;

public enum FieldState {
    PLAYER_ONE(1),
    PLAYER_TWO(2),
    EMPTY_FIELD(0);

    private int type;

    FieldState(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
