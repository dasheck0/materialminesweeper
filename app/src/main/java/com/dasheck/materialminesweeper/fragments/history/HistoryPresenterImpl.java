package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetChartValuesInteractor;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetGameInformationListInteractor;
import com.dasheck.model.models.ChartValues;
import com.dasheck.model.models.Filter;
import com.dasheck.model.models.ValueSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class HistoryPresenterImpl extends BasePresenterImpl implements HistoryPresenter {

  @Inject HistoryView view;
  @Inject GetGameInformationListInteractor getGameInformationListInteractor;
  @Inject GetChartValuesInteractor getChartValuesInteractor;

  private Filter currentFilter = new Filter();

  @Override public void onResume() {
    super.onResume();
    applyInitialFilter();

    getChartValuesInteractor.execute().subscribe(view::setChartValues);
/*
    List<String> keys = Arrays.asList("Jan", "Feb", "Mar");
    List<Float> values = Arrays.asList(0.0f, 50.0f, 25.0f);

    view.setChartValues(new ChartValues(Collections.singletonList(new ValueSet(keys, values ))));*/
  }

  @Override public void openFilterDialog() {
    view.openFilterDialog(currentFilter);
  }

  @Override public void applyInitialFilter() {
    currentFilter = new Filter(true, true, true, true, true, true);
    applyFilter();
  }

  @Override public void applyFilter(Filter filter) {
    currentFilter = filter;
    applyFilter();
  }

  private void applyFilter() {
    getGameInformationListInteractor.execute(currentFilter).subscribe(view::setGameInformationList);
  }
}
