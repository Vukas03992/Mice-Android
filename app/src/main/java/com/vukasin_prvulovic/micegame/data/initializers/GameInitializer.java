package com.vukasin_prvulovic.micegame.data.initializers;

import android.content.Context;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.GameState;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;


public class GameInitializer {

    private FieldInitializer fieldInitializer;
    private Context context;

    public GameInitializer(FieldInitializer fieldInitializer, Context context) {
        this.fieldInitializer = fieldInitializer;
        this.context = context;
    }

    public Game initGame(){
        Game game=new Game();

        game.setGameState(new GameState());
        game.setFields(fieldInitializer.initFields());
        game.setMessage(context.getString(R.string.board_fragment_game_message));

        return game;
    }
}
