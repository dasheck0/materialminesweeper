package com.dasheck.materialminesweeper.fragments.game.interactors;

import android.support.v4.util.Pair;
import com.dasheck.model.datastores.FieldDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public class CreateFieldInteractorImpl implements CreateFieldInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public CreateFieldInteractorImpl() {
  }

  @Override
  public Observable<Pair<Integer, Integer>> execute(int columnCount, int rowCount, int difficulty) {
    return fieldDatastore.create(columnCount, rowCount, difficulty)
        .map(field -> new Pair<>(field.getWidth(), field.getHeight()));
  }
}
