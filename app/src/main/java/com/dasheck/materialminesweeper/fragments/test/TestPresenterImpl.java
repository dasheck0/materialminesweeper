package com.dasheck.materialminesweeper.fragments.test;

import com.dasheck.materialminesweeper.fragments.BasePresenter;
import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.materialminesweeper.fragments.test.interactors.GetTileListInteractor;
import com.dasheck.model.models.Field;
import com.dasheck.model.utilities.Utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.prefs.PreferenceChangeEvent;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by s.neidig on 1701/16.
 */
public class TestPresenterImpl extends BasePresenterImpl implements TestPresenter {

  @Inject TestView view;
  @Inject GetTileListInteractor getTileListInteractor;

  @Override public void onResume() {
    super.onResume();

    Timber.d("Calling on resumer");

    getTileListInteractor.execute().subscribe(view::setBombs);
  }
}
