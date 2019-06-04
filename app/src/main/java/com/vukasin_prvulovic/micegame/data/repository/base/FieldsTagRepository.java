package com.vukasin_prvulovic.micegame.data.repository.base;

import java.util.List;

import io.reactivex.Observable;

public interface FieldsTagRepository {

    Observable<List<String>> getFieldsTags();
}
