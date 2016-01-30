package com.dasheck.model.datastores;

import com.dasheck.model.models.GameMode;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface GameModeDatastore {

  Observable<List<GameMode>> get();
}
