package com.dasheck.materialminesweeper.fragments.game.interactors;

import android.support.v4.util.Pair;
import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.models.Configuration;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public class CreateFieldInteractorImpl implements CreateFieldInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public CreateFieldInteractorImpl() {
  }

  @Override public Observable<Pair<Integer, Integer>> execute(Configuration configuration) {
    return currentGameController.startGame(configuration)
        .flatMap(x -> currentGameController.getField())
        .map(field -> new Pair<>(field.getWidth(), field.getHeight()));
  }
}
