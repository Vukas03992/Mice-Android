package com.vukasin_prvulovic.micegame.data.local.manager;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerApplication
public class FieldTagManager {

    private AssetManager assetManager;
    private Gson gson;

    @Inject
    public FieldTagManager(AssetManager assetManager, Gson gson) {
        this.assetManager = assetManager;
        this.gson = gson;
    }

    @SuppressWarnings("unchecked")
    public Observable<List<String>> readFieldsTags(){

        String jsonTags=null;
        try{
            InputStream inputStream=assetManager.open("tags.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonTags = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }

        List<String> tags=gson.fromJson(jsonTags,List.class);

        return Observable.just(tags);
    }
}
