package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.model.models.BackgroundMusic;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface GetAvailableBackgroundMusicInteractor {

  Observable<List<BackgroundMusic>> execute();
}
