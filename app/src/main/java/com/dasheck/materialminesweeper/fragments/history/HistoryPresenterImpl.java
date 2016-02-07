package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetGameInformationListInteractor;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class HistoryPresenterImpl extends BasePresenterImpl implements HistoryPresenter {

  @Inject HistoryView view;
  @Inject GetGameInformationListInteractor getGameInformationListInteractor;

  @Override public void onResume() {
    super.onResume();

    getGameInformationListInteractor.execute().subscribe(view::setGameInformationList);
  }
}
