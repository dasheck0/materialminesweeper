package com.dasheck.materialminesweeper.controllers;

import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface PreferencesController {

  Observable<Void> writeList(List<Integer> list);

  Observable<List<Integer>> readList();
}
