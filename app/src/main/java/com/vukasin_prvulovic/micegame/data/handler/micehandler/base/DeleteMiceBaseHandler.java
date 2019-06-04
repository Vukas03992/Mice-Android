package com.vukasin_prvulovic.micegame.data.handler.micehandler.base;

import com.vukasin_prvulovic.micegame.data.handler.micehandler.model.Mice;
import com.vukasin_prvulovic.micegame.data.model.Field;

public class DeleteMiceBaseHandler {

    private Mice mice;
    private Field previousField;
    private Field nextField;
    private DeleteMiceBaseHandler next;

    public DeleteMiceBaseHandler(Mice mice, Field previousField,Field nextField) {
        this.mice = mice;
        this.previousField = previousField;
        this.nextField=nextField;
    }

    public void handler(){
        if(next!=null) next.handler();
    }

    public void add(DeleteMiceBaseHandler deleteMiceBaseHandler){
        if (next!=null){
            next.add(deleteMiceBaseHandler);
        }else{
            next=deleteMiceBaseHandler;
        }
    }

    public Mice getMice() {
        return mice;
    }

    public Field getPreviousField() {
        return previousField;
    }

    public Field getNextField() {
        return nextField;
    }
}
