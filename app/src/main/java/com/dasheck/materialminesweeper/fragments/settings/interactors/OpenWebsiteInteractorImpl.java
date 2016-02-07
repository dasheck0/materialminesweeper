package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.materialminesweeper.controllers.WebController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class OpenWebsiteInteractorImpl implements OpenWebsiteInteractor {

  @Inject WebController webController;

  @Inject public OpenWebsiteInteractorImpl() {
  }

  @Override public Observable<Void> execute(String url) {
    return webController.openWebsite(url);
  }
}
