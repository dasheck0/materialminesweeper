package com.dasheck.model.datastores;

import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.GameMode;
import java.util.ArrayList;
import java.util.Arrays;
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

        modes.add(new GameMode("Easy", Arrays.asList(new Configuration(10, 10, FieldDatastore.DIFFICULTY_EASY))));
        modes.add(new GameMode("Medium", Arrays.asList(new Configuration(20, 20, FieldDatastore.DIFFICULTY_MEDIUM))));
        modes.add(new GameMode("Hard", Arrays.asList(new Configuration(30, 30, FieldDatastore.DIFFICULTY_HARD))));
        modes.add(new GameMode("XMetriX", Arrays.asList(new Configuration(40, 40, FieldDatastore.DIFFICULTY_XMETIRX))));

        subscriber.onNext(modes);
        subscriber.onCompleted();
      }
    });
  }
}
