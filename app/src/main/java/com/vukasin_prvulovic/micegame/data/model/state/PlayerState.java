package com.vukasin_prvulovic.micegame.data.model.state;

public enum PlayerState {
    PLAYER_ONE(1),
    PLAYER_TWO(2);

    private int type;

    PlayerState(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
