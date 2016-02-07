package com.dasheck.materialminesweeper.controllers;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface WebController {

  Observable<Void> openWebsite(String url);
}
