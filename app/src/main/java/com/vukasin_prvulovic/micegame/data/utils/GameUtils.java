package com.vukasin_prvulovic.micegame.data.utils;

import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.GameState;
import com.vukasin_prvulovic.micegame.data.model.state.MiceMade;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;
import com.vukasin_prvulovic.micegame.data.model.state.SetState;
import com.vukasin_prvulovic.micegame.data.model.state.WinnerState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameUtils {

    public static Game copyGame(Game game){
        Game newGame=new Game();
        newGame.setNumberOfPlayers(game.getNumberOfPlayers());
        newGame.setFields(copyFields(game.getFields()));
        newGame.setGameState(copyGameState(game.getGameState()));

        return newGame;
    }

    private static Map<String,Field> copyFields(Map<String,Field> fieldsMap){
        Map<String,Field> newMap=new HashMap<>();
        for (Map.Entry<String, Field> stringFieldEntry : fieldsMap.entrySet()) {
            newMap.put(stringFieldEntry.getKey(),copyField(stringFieldEntry.getValue()));
        }

        for (Field field : fieldsMap.values()) {
            Field newField=newMap.get(field.getId());

            List<Field> newNeighbors=new ArrayList<>();
            for (Field neighborField : field.getNeighbors()) {
                newNeighbors.add(newMap.get(neighborField.getId()));
            }
            newField.setNeighbors(newNeighbors);
        }

        return newMap;
    }

    private static GameState copyGameState(GameState gameState){
        GameState newGameState=new GameState();

        newGameState.setSetState(gameState.getSetState());
        newGameState.setWinnerState(gameState.getWinnerState());
        newGameState.setNextPlayerState(gameState.getNextPlayerState());
        newGameState.setMiceMade(gameState.getMiceMade());

        return newGameState;
    }

    private static Field copyField(Field field){
        Field newField=new Field();

        newField.setId(field.getId());
        newField.setRow(field.getRow());
        newField.setCol(field.getCol());
        newField.setMiceState(field.getMiceState());
        newField.setFieldType(field.getFieldType());
        newField.setFieldState(field.getFieldState());

        return newField;
    }
}
