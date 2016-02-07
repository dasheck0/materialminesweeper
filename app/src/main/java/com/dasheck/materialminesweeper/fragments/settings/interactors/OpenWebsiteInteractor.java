package com.dasheck.materialminesweeper.fragments.settings.interactors;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface OpenWebsiteInteractor {

  Observable<Void> execute(String url);
}
