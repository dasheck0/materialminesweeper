package com.dasheck.materialminesweeper.fragments.gamemenu_item.interactors;

import com.dasheck.model.models.GameStatistics;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface ResetGameStatisticsInteractor {

  Observable<GameStatistics> execute(int difficulty);
}
