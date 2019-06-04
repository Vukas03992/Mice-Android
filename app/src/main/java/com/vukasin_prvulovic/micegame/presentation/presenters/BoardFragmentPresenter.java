package com.vukasin_prvulovic.micegame.presentation.presenters;

import android.util.Log;

import com.vukasin_prvulovic.micegame.data.eventbus.EventBus;
import com.vukasin_prvulovic.micegame.data.model.Game;
import com.vukasin_prvulovic.micegame.data.model.state.MiceMade;
import com.vukasin_prvulovic.micegame.data.model.state.PlayerState;
import com.vukasin_prvulovic.micegame.data.model.state.SetState;
import com.vukasin_prvulovic.micegame.data.model.state.WinnerState;
import com.vukasin_prvulovic.micegame.domain.base.interaction.DefaultObserver;
import com.vukasin_prvulovic.micegame.domain.usecases.GetLastGameUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.GetMusicPermissionUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.GetSoundPermissionUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.GetTipsPermissionUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.PlayGameUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.ReadFieldsTagsUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.ReadPlayerNameUseCase;
import com.vukasin_prvulovic.micegame.domain.usecases.SaveMusicPermission;
import com.vukasin_prvulovic.micegame.domain.usecases.SaveSoundPermission;
import com.vukasin_prvulovic.micegame.domain.usecases.UndoGameUseCase;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.base.presenter.Presenter;
import com.vukasin_prvulovic.micegame.presentation.view.activities.SoundPlayer;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.abstraction.BoardView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import static android.content.ContentValues.TAG;

@PerFragment
public class BoardFragmentPresenter extends Presenter{

    private BoardView view;
    @Inject
    ReadFieldsTagsUseCase readFieldsTagsUseCase;
    @Inject
    ReadPlayerNameUseCase readPlayerNameUseCase;
    @Inject
    PlayGameUseCase playGameUseCase;
    @Inject
    UndoGameUseCase undoGameUseCase;
    @Inject
    GetLastGameUseCase getLastGameUseCase;
    @Inject
    SoundPlayer soundPlayer;
    @Inject
    @Named("exit")
    EventBus<Boolean> eventBusExit;
    @Inject
    GetMusicPermissionUseCase getMusicPermissionUseCase;
    @Inject
    GetSoundPermissionUseCase getSoundPermissionUseCase;
    @Inject
    SaveMusicPermission saveMusicPermission;
    @Inject
    SaveSoundPermission saveSoundPermission;
    @Inject
    GetTipsPermissionUseCase getTipsPermissionUseCase;

    private Game game;

    private String previousField;

    private boolean musicPermission;
    private boolean soundPermission;

    @Inject
    public BoardFragmentPresenter(BoardView view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        readFieldsTagsUseCase.execute(new ReadFieldsTagsObserver());
        readPlayerNameUseCase.execute(new ReadPlayerNameObservable());
        initPermissions();
        getTipsPermissionUseCase.execute(new TipsObserver());
    }

    @Override
    public void onStop() {
        readFieldsTagsUseCase.dispose();
        readPlayerNameUseCase.dispose();
        playGameUseCase.dispose();
        undoGameUseCase.dispose();
        getTipsPermissionUseCase.dispose();
    }

    public void actionDown(String tag, int x, int y){

        if (game.getGameState().getMiceMade()!= MiceMade.MICA_IS_MADE){
            if (game.getGameState().getSetState()== SetState.STILL_SET) {
                if (tag.equals("basketOne")) {
                    if (game.getGameState().getNextPlayerState() == PlayerState.PLAYER_ONE) {
                        view.setOnMovable(x, y, PlayerState.PLAYER_ONE);
                        previousField = tag;
                        soundPlayer.playTakeEffect();
                    }
                } else if (tag.equals("basketTwo")) {
                    if (game.getGameState().getNextPlayerState() == PlayerState.PLAYER_TWO) {
                        view.setOnMovable(x, y, PlayerState.PLAYER_TWO);
                        previousField = tag;
                        soundPlayer.playTakeEffect();
                    }
                }
            }else{
                switch (game.getFields().get(tag).getFieldState().getType()) {
                    case 1:
                        if (game.getGameState().getNextPlayerState()==PlayerState.PLAYER_ONE) {
                            view.setOnMovable(x, y, PlayerState.PLAYER_ONE);
                            view.clearField(tag);
                            previousField = tag;
                            soundPlayer.playTakeEffect();
                        }
                        break;
                    case 2:
                        if (game.getGameState().getNextPlayerState()==PlayerState.PLAYER_TWO) {
                            view.setOnMovable(x, y, PlayerState.PLAYER_TWO);
                            view.clearField(tag);
                            previousField = tag;
                            soundPlayer.playTakeEffect();
                        }
                        break;
                }
            }
        }else{
            previousField=tag;
        }
    }

    public void actionMove(int x, int y){
        view.settingMovable(x,y);
    }

    public void actionUp(String tag){

        view.setOffMovable();
        if (previousField!=null) {
            playGameUseCase.execute(new PlayGameObserver(),game, previousField, tag);
            soundPlayer.playPutEffect();
        }
        previousField=null;
    }

    public void undo(){
        undoGameUseCase.execute(new PlayGameObserver());
    }

    public void loadGame(){
        getLastGameUseCase.execute(new PlayGameObserver());
    }

    public void exit(){
        eventBusExit.send(true);
    }

    public void playClickSound(){
        soundPlayer.playPutEffect();
    }

    public void saveMusicPermission(){
        musicPermission=!musicPermission;
        saveMusicPermission.execute(new MusicObserver(),musicPermission);
    }

    public void saveSoundPermission(){
        soundPermission=!soundPermission;
        saveSoundPermission.execute(new SoundObserver(),soundPermission);
    }

    private class ReadFieldsTagsObserver extends DefaultObserver<List<String>>{
        @Override
        public void onNext(List<String> strings) {
            view.setFields(strings);
        }
    }

    private class ReadPlayerNameObservable extends DefaultObserver<String[]>{
        @Override
        public void onNext(String[] strings) {
            view.setPlayerName(strings[0],strings[1]);
            view.setBasketsOn();
        }
    }

    private class PlayGameObserver extends DefaultObserver<Game>{
        @Override
        public void onNext(Game game) {
                BoardFragmentPresenter.this.game = game;
                view.drawBoard(game.getFields());
                view.setPlayerTurn(game.getGameState().getNextPlayerState());
                if (game.getGameState().getSetState() == SetState.SET_OVER) {
                    view.setBasketsOff();
                }
                if (game.getNumberOfPlayers() >= 0) {
                    String[] tokens = getNumberOfTokens(game.getNumberOfPlayers());
                    view.setNumberOfTokens(tokens[0], tokens[1]);
                }
                if (game.getGameState().getMiceMade()==MiceMade.MICA_IS_MADE){
                    soundPlayer.playMiceEffect();
                }
                if (game.getGameState().getWinnerState()== WinnerState.WINNER_ONE ||
                        game.getGameState().getWinnerState()== WinnerState.WINNER_TWO){
                    view.showWinner();
                }
            }
    }

    private class MusicObserver extends DefaultObserver<Boolean>{
        @Override
        public void onNext(Boolean aBoolean) {
            musicPermission=aBoolean;
            view.setMusicIcon(aBoolean);
        }
    }

    private class SoundObserver extends DefaultObserver<Boolean>{
        @Override
        public void onNext(Boolean aBoolean) {
            soundPermission=aBoolean;
            view.setSoundIcon(aBoolean);
        }
    }

    private class TipsObserver extends DefaultObserver<Boolean>{
        @Override
        public void onNext(Boolean aBoolean) {
            if (aBoolean){
                view.showTips();
            }
            Log.e(TAG, "onNext: pustati tips "+aBoolean );
        }
    }

    private String[] getNumberOfTokens(int numberOfPlayers){
        String[] numbersOfTokens=new String[2];
        if (numberOfPlayers%2==0){
            numbersOfTokens[0]="more: "+numberOfPlayers/2;
            numbersOfTokens[1]="more: "+numberOfPlayers/2;
        }else{
            numbersOfTokens[0]="more: "+numberOfPlayers/2;
            numbersOfTokens[1]="more: "+(numberOfPlayers/2+1);
        }
        return numbersOfTokens;
    }

    private void initPermissions(){
        getMusicPermissionUseCase.execute(new MusicObserver());
        getSoundPermissionUseCase.execute(new SoundObserver());
    }
}
