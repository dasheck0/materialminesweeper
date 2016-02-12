package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetChartValuesInteractor;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetGameInformationListInteractor;
import com.dasheck.model.models.ChartValues;
import com.dasheck.model.models.Filter;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class HistoryPresenterImpl extends BasePresenterImpl implements HistoryPresenter {

  @Inject HistoryView view;
  @Inject GetGameInformationListInteractor getGameInformationListInteractor;
  @Inject GetChartValuesInteractor getChartValuesInteractor;

  private Filter currentFilter = new Filter();
  private ChartValues chartValues;

  @Override public void onResume() {
    super.onResume();
    applyInitialFilter();

    getChartValuesInteractor.execute().subscribe(this::setChartValues);
  }

  private void setChartValues(ChartValues chartValues) {
    this.chartValues = chartValues;
    view.setChartTypes(new ArrayList<>(chartValues.getValueSets().keySet()));
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

  @Override public void loadChartValues(String chartType) {
    if (chartValues != null) {
      view.setChartValues(chartValues.getValueSets().get(chartType));
    }
  }

  private void applyFilter() {
    getGameInformationListInteractor.execute(currentFilter).subscribe(view::setGameInformationList);
  }
}
