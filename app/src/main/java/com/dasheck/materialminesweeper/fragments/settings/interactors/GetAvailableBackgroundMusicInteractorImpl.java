package com.dasheck.materialminesweeper.fragments.settings.interactors;

import android.media.audiofx.BassBoost;
import com.dasheck.model.datastores.SettingsDatastore;
import com.dasheck.model.models.BackgroundMusic;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class GetAvailableBackgroundMusicInteractorImpl implements GetAvailableBackgroundMusicInteractor {

  @Inject SettingsDatastore settingsDatastore;

  @Inject public GetAvailableBackgroundMusicInteractorImpl() {
  }

  @Override public Observable<List<BackgroundMusic>> execute() {
    return settingsDatastore.getAvailableBackgroundMusic();
  }
}
