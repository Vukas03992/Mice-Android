package com.vukasin_prvulovic.micegame.data.local.manager;

import android.content.SharedPreferences;

import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

import static com.vukasin_prvulovic.micegame.data.utils.Constants.PLAYER_ONE_SP;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.PLAYER_TWO_SP;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.SHARED_PREFERENCES_PLAYER_NAME;

@PerApplication
public class PlayerNameManager {

    private SharedPreferences sharedPreferences;

    @Inject
    public PlayerNameManager(@Named(SHARED_PREFERENCES_PLAYER_NAME) SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Observable<Boolean> savePlayersNames(String playerOne, String playerTwo){
        if (sharedPreferences!=null){
            sharedPreferences.edit()
                    .putString(PLAYER_ONE_SP,playerOne)
                    .putString(PLAYER_TWO_SP,playerTwo)
                    .apply();
            return Observable.just(true);
        }
        return Observable.just(false);
    }

    public Observable<String[]> readePlayersNames(){
        String[] names=new String[2];
        String playerOne=sharedPreferences.getString(PLAYER_ONE_SP,null);
        String playerTwo=sharedPreferences.getString(PLAYER_TWO_SP, null);
        if (playerOne!=null && !playerOne.equals("")){
            names[0]=playerOne;
        }else{
            names[0]="Player One";
        }
        if (playerTwo!=null && !playerTwo.equals("")){
            names[1]=playerTwo;
        }else{
            names[1]="Player Two";
        }
        return Observable.just(names);
    }
}
