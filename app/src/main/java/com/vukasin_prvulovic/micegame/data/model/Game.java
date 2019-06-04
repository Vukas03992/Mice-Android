package com.vukasin_prvulovic.micegame.data.model;

import com.vukasin_prvulovic.micegame.data.model.state.FieldState;
import com.vukasin_prvulovic.micegame.data.model.state.GameState;
import java.util.Map;

public class Game {

    private Map<String, Field> fields;
    private GameState gameState;
    private int numberOfPlayers=18;
    private String message=null;

    public Map<String, Field> getFields() {
        return fields;
    }

    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("Fields: ");
        for (Field field : fields.values()) {
            if (field.getFieldState()==FieldState.PLAYER_ONE || field.getFieldState()==FieldState.PLAYER_TWO) {
                builder.append("(").append(field.getId()).append(")[");
                if (field.getFieldState() == FieldState.PLAYER_ONE) {
                    builder.append("player one");
                } else if (field.getFieldState() == FieldState.PLAYER_TWO) {
                    builder.append("player two");
                }
                builder.append("],");
            }
        }
        builder.append("\n");
        return builder.toString();
    }
}
