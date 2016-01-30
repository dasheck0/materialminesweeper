package com.dasheck.model.datastores;

import com.dasheck.model.models.GameMode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class GameModeDatastoreImpl implements GameModeDatastore {

  @Inject public GameModeDatastoreImpl() {
  }

  @Override public Observable<List<GameMode>> get() {
    return Observable.create(new Observable.OnSubscribe<List<GameMode>>() {
      @Override public void call(Subscriber<? super List<GameMode>> subscriber) {
        List<GameMode> modes = new ArrayList<>();

        modes.add(new GameMode("Easy", FieldDatastore.DIFFICULTY_EASY));
        modes.add(new GameMode("Medium", FieldDatastore.DIFFICULTY_HARD));
        modes.add(new GameMode("Hard", FieldDatastore.DIFFICULTY_MEDIUM));
        modes.add(new GameMode("XMetriX", FieldDatastore.DIFFICULTY_XMETIRX));

        subscriber.onNext(modes);
        subscriber.onCompleted();
      }
    });
  }
}
