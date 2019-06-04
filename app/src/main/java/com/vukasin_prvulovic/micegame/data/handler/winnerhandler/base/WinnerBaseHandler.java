package com.vukasin_prvulovic.micegame.data.handler.winnerhandler.base;

import com.vukasin_prvulovic.micegame.data.model.Game;

public class WinnerBaseHandler {

    private Game game;
    private WinnerBaseHandler next;

    public WinnerBaseHandler(Game game) {
        this.game = game;
    }

    public void handle(){
        if (next!=null) next.handle();
    }

    public void add(WinnerBaseHandler handler){
        if (next!=null){
            next.add(handler);
        }else{
            next=handler;
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
