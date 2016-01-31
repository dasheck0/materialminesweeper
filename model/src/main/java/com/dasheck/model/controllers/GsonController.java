package com.dasheck.model.controllers;

import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface GsonController {

  <T> Observable<List<T>> listFromJson(String json, Class<T[]> type);

  <T> Observable<T> fromJson(String json, Class<T> type);

  <T> Observable<String> listToJson(List<T> list);

  <T> Observable<String> toJson(T obj);
}
