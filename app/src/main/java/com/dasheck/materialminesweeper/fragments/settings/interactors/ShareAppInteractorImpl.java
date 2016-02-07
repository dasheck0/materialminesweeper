package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.materialminesweeper.controllers.ShareController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class ShareAppInteractorImpl implements ShareAppInteractor {

  @Inject ShareController shareController;

  @Inject public ShareAppInteractorImpl() {
  }

  @Override public Observable<Void> execute() {
    return shareController.shareContent("This is a really cool app. Check it out");
  }
}
