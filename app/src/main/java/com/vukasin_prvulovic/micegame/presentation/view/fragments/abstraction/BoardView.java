package com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction;

import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;

import java.util.List;
import java.util.Map;

public interface BoardView {

    void setFields(List<String> tags);
    void setPlayerName(String playerOne, String playerTwo);
    void setPlayerTurn(PlayerState nextPlayerState);
    void setNumberOfTokens(String playerOneTokens, String playerTwoTokens);

    void setBasketsOn();
    void setBasketsOff();

    void setOnMovable(int x,int y, PlayerState playerState);
    void settingMovable(int x,int y);
    void setOffMovable();

    void drawBoard(Map<String,Field> fields);
    void clearField(String tag);

    void setMusicIcon(boolean set);
    void setSoundIcon(boolean set);

    void showTips();

    void showWinner();
}
