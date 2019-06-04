package com.vukasin_prvulovic.micegame.data.local.manager;

import android.content.SharedPreferences;
import android.util.Log;

import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import javax.inject.Inject;
import javax.inject.Named;

import static android.content.ContentValues.TAG;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.SHARED_PREFERENCES_TIPS;
import static com.vukasin_prvulovic.micegame.data.utils.Constants.TIPS;

@PerApplication
public class TipsManager {

    private SharedPreferences sharedPreferences;

    @Inject
    public TipsManager(@Named(SHARED_PREFERENCES_TIPS) SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public boolean saveTipsPermission(boolean permission){
        sharedPreferences.edit()
                .putBoolean(TIPS,permission)
                .apply();
        return permission;
    }

    public boolean getTipsPermission(){
        return sharedPreferences.getBoolean(TIPS,true);
    }
}
