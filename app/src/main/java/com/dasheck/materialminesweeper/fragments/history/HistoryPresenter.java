package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.model.models.Filter;

/**
 * @author Stefan Neidig
 */
public interface HistoryPresenter extends BasePresenter {

  void openFilterDialog();

  void applyInitialFilter();

  void applyFilter(Filter filter);
}
