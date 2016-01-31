package com.dasheck.materialminesweeper.controllers;

import android.content.SharedPreferences;
import com.dasheck.model.controllers.GsonController;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class PreferencesControllerImpl implements PreferencesController {

  @Inject SharedPreferences sharedPreferences;
  @Inject GsonController gsonController;

  @Inject public PreferencesControllerImpl() {
  }

  @Override public Observable<Void> writeList(List<Integer> list) {
    return gsonController.listToJson(list)
        .map(json -> sharedPreferences.edit().putString("JSON", json).commit())
        .map(x -> null);
  }

  @Override public Observable<List<Integer>> readList() {
    return gsonController.listFromJson(sharedPreferences.getString("JSON", "[]"), Integer[].class);
  }
}
