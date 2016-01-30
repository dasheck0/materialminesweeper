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

        modes.add(new GameMode("Easy", FieldDatastore.DIFFICULTY_EASY, Arrays.asList(new Configuration(10, 10))));
        modes.add(new GameMode("Medium", FieldDatastore.DIFFICULTY_HARD, Arrays.asList(new Configuration(20, 20))));
        modes.add(new GameMode("Hard", FieldDatastore.DIFFICULTY_MEDIUM, Arrays.asList(new Configuration(30, 30))));
        modes.add(new GameMode("XMetriX", FieldDatastore.DIFFICULTY_XMETIRX, Arrays.asList(new Configuration(40, 40))));

        subscriber.onNext(modes);
        subscriber.onCompleted();
      }
    });
  }
}
