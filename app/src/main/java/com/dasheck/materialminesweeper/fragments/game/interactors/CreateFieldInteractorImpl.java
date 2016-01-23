package com.dasheck.materialminesweeper.fragments.game.interactors;

import android.support.v4.util.Pair;
import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Field;
import javax.inject.Inject;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by s.neidig on 23/01/16.
 */
public class CreateFieldInteractorImpl implements CreateFieldInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public CreateFieldInteractorImpl() {
  }

  @Override public Observable<Pair<Integer, Integer>> execute() {
    return fieldDatastore.create(20, 20, FieldDatastore.DIFFICULTY_EASY)
        .map(field -> new Pair<Integer, Integer>(field.getWidth(), field.getHeight()));
  }
}
