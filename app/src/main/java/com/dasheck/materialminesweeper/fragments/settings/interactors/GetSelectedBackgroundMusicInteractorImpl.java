package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.model.datastores.SettingsDatastore;
import com.dasheck.model.models.BackgroundMusic;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class GetSelectedBackgroundMusicInteractorImpl implements GetSelectedBackgroundMusicInteractor {

  @Inject SettingsDatastore settingsDatastore;

  @Inject public GetSelectedBackgroundMusicInteractorImpl() {
  }

  @Override public Observable<BackgroundMusic> execute() {
    return settingsDatastore.getSelectedBackgroundMusic();
  }
}
