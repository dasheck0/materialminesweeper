package com.dasheck.materialminesweeper.fragments.profile;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import javax.inject.Inject;

/**
 * @author Stefan Neidig
 */
public class ProfilePresenterImpl extends BasePresenterImpl implements ProfilePresenter {

  @Inject ProfileView view;

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void registerNewImage() {
    view.startImageIntent(); //// TODO: 14/02/16 controller
  }
}
