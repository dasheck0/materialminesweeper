package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.controllers.SoundController;
import com.dasheck.materialminesweeper.controllers.VibrationController;
import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.datastores.SettingsDatastore;
import com.dasheck.model.models.Tile;
import javax.inject.Inject;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by s.neidig on 23/01/16.
 */
public class MarkTileInteractorImpl implements MarkTileInteractor {

  @Inject CurrentGameController currentGameController;
  @Inject VibrationController vibrationController;
  @Inject SettingsDatastore settingsDatastore;
  @Inject SoundController soundController;

  @Inject public MarkTileInteractorImpl() {
  }

  @Override public Observable<Void> execute(Tile tile) {
    return Observable.zip(currentGameController.markTile(tile.getPosition()), settingsDatastore.isVibrationEnabled(),
        (x, vibrationEnabled) -> {
          if (vibrationEnabled) {
            return vibrationController.vibrate(VibrationController.VIBRATION_SHORT);
          } else {
            return null;
          }
        }).flatMap(y -> soundController.playSoundEffect(R.raw.chime));
  }
}
