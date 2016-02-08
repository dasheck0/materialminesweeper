package com.dasheck.materialminesweeper.fragments.history.interactors;

import com.dasheck.model.models.ChartValues;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface GetChartValuesInteractor {

  Observable<ChartValues> execute();
}
