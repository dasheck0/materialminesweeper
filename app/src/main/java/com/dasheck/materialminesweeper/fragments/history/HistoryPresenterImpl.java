package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetGameInformationListInteractor;
import com.dasheck.model.models.Filter;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class HistoryPresenterImpl extends BasePresenterImpl implements HistoryPresenter {

  @Inject HistoryView view;
  @Inject GetGameInformationListInteractor getGameInformationListInteractor;

  private Filter currentFilter = new Filter();

  @Override public void onResume() {
    super.onResume();
    applyInitialFilter();
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
