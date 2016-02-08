package com.dasheck.materialminesweeper.fragments.history;

import android.content.Context;
import com.dasheck.materialminesweeper.adapters.GameInformationListAdapter;
import com.dasheck.materialminesweeper.annotations.PerFragment;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetChartValuesInteractor;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetChartValuesInteractorImpl;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetGameInformationListInteractor;
import com.dasheck.materialminesweeper.fragments.history.interactors.GetGameInformationListInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * @author Stefan Neidig
 */
@Module public class HistoryModule {

  private HistoryView view;
  private HistoryPresenter presenter;

  public HistoryModule(HistoryView view, HistoryPresenter presenter) {
    this.view = view;
    this.presenter = presenter;
  }

  @PerFragment @Provides HistoryView provideView() {
    return view;
  }

  @PerFragment @Provides HistoryPresenter providePresenter() {
    return presenter;
  }

  @PerFragment @Provides GameInformationListAdapter provideGameInformationListAdapter(Context context) {
    return new GameInformationListAdapter(context);
  }

  @Provides @PerFragment public GetGameInformationListInteractor provideGetGameInformationListInteractor(
      GetGameInformationListInteractorImpl getGameInformationListInteractorImpl) {
    return getGameInformationListInteractorImpl;
  }

  @Provides @PerFragment public GetChartValuesInteractor provideGetChartValuesInteractor(
      GetChartValuesInteractorImpl getChartValuesInteractorImpl) {
    return getChartValuesInteractorImpl;
  }
}
