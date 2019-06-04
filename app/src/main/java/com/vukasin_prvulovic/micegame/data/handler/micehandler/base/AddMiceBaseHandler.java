package com.vukasin_prvulovic.micegame.data.handler.micehandler.base;

import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;

public class AddMiceBaseHandler {

    private Mice mice;
    private Field nextField;
    private PlayerState playerState;
    private AddMiceBaseHandler next;

    public AddMiceBaseHandler(Mice mice, Field nextField, PlayerState playerState) {
        this.mice = mice;
        this.nextField = nextField;
        this.playerState = playerState;
    }

    public void handle(){
        if (next!=null) next.handle();
    }

    public void add(AddMiceBaseHandler addMiceBaseHandler){
        if (next!=null){
            next.add(addMiceBaseHandler);
        }else{
            next= addMiceBaseHandler;
        }
    }

    public Mice getMice() {
        return mice;
    }

    public Field getNextField() {
        return nextField;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
