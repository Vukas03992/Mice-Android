package com.vukasin_prvulovic.micegame.data.initializers;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.vukasin_prvulovic.micegame.data.model.Field;
import com.vukasin_prvulovic.micegame.data.model.mapper.FieldMapper;
import com.vukasin_prvulovic.micegame.data.model.FieldType;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@PerApplication
public class FieldInitializer {

    private Gson gson;
    private AssetManager assetManager;

    @Inject
    public FieldInitializer(Gson gson, AssetManager assetManager) {
        this.gson = gson;
        this.assetManager = assetManager;
    }

    public Map<String, Field> initFields(){

        String jsonFields=null;
        try{
            InputStream inputStream=assetManager.open("fields.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonFields = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }

        FieldMapper[] fieldMappers=gson.fromJson(jsonFields,FieldMapper[].class);
        Map<String,Field> fieldMap=new HashMap<>();
        for (FieldMapper fieldMapper : fieldMappers) {
            Field field=new Field();
            field.setId(fieldMapper.getRow()+"_"+fieldMapper.getCol());
            field.setRow(fieldMapper.getRow());
            field.setCol(fieldMapper.getCol());
            fieldMap.put(field.getId(),field);
            switch (fieldMapper.getFieldType()){
                case "primary": field.setFieldType(FieldType.PRIMARY);
                break;
                case "secondary": field.setFieldType(FieldType.SECONDARY);
                break;
                case "ternary":field.setFieldType(FieldType.TERNARY);
                break;
            }
        }

        for (FieldMapper fieldMapper : fieldMappers) {
            String id=fieldMapper.getRow()+"_"+fieldMapper.getCol();
            List<Field> neighbors=new ArrayList<>();
            for (String s : fieldMapper.getNeighbors()) {
                Field field=fieldMap.get(s);
                neighbors.add(field);
            }
            Field field=fieldMap.get(id);
            field.setNeighbors(neighbors);
        }
        return fieldMap;
    }
}
