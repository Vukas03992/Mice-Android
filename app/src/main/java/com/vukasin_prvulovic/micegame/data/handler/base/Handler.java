package com.vukasin_prvulovic.micegame.data.handler.base;

import com.vukasin_prvulovic.micegame.data.local.manager.GameSnapshotManager;
import com.vukasin_prvulovic.micegame.data.model.Game;

public class Handler {

    private Game game;
    private Handler next;
    private String previousField;
    private String nextField;
    private GameSnapshotManager manager;

    public Handler(GameSnapshotManager manager,Game game, String previousField, String nextField) {
        this.game = game;
        this.previousField=previousField;
        this.nextField=nextField;
        this.manager=manager;
    }

    public Game handle(){
       if (next!=null) return next.handle();
       else return game;
    }

    public void add(Handler handler){
        if (next!=null){
            next.add(handler);
        }else
        next=handler;
    }

    public Game getGame() {
        return game;
    }

    public String getPreviousField() {
        return previousField;
    }

    public String getNextField() {
        return nextField;
    }

    public GameSnapshotManager getManager() {
        return manager;
    }
}
