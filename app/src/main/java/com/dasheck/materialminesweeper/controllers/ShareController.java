package com.dasheck.materialminesweeper.controllers;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface ShareController {

  Observable<Void> shareContent(String content);
}
