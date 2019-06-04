package com.vukasin_prvulovic.micegame.data.model.state;

public class GameState {

    private PlayerState nextPlayerState = PlayerState.PLAYER_ONE;
    private MiceMade miceMade = MiceMade.MICA_IS_NOT_MADE;
    private SetState setState = SetState.STILL_SET;
    private WinnerState winnerState=WinnerState.NO_WINNER;

    public PlayerState getNextPlayerState() {
        return nextPlayerState;
    }

    public void setNextPlayerState(PlayerState nextPlayerState) {
        this.nextPlayerState = nextPlayerState;
    }

    public MiceMade getMiceMade() {
        return miceMade;
    }

    public void setMiceMade(MiceMade miceMade) {
        this.miceMade = miceMade;
    }

    public SetState getSetState() {
        return setState;
    }

    public void setSetState(SetState setState) {
        this.setState = setState;
    }

    public WinnerState getWinnerState() {
        return winnerState;
    }

    public void setWinnerState(WinnerState winnerState) {
        this.winnerState = winnerState;
    }
}
