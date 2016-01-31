package com.dasheck.model.controllers;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * @author Daniel Nagel
 */
public class GsonControllerImpl implements GsonController {

  private Gson gson;

  @Inject public GsonControllerImpl(Gson gson) {
    this.gson = gson;
  }

  @Override public <T> Observable<List<T>> listFromJson(String json, Class<T[]> type) {
    if (json.length() == 0) {
      json = "[]";
    }

    final String finalJson = json;
    return Observable.create(new Observable.OnSubscribe<List<T>>() {
      @Override public void call(Subscriber<? super List<T>> subscriber) {
        T[] array = gson.fromJson(finalJson, type);
        subscriber.onNext(new ArrayList<>(Arrays.asList(array)));
        subscriber.onCompleted();
      }
    });
  }

  @Override public <T> Observable<T> fromJson(String json, Class<T> type) {
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override public void call(Subscriber<? super T> subscriber) {
        T obj = gson.fromJson(json, type);
        subscriber.onNext(obj);
        subscriber.onCompleted();
      }
    });
  }

  @Override public <T> Observable<String> listToJson(List<T> list) {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        String result = gson.toJson(list);
        subscriber.onNext(result);
        subscriber.onCompleted();
      }
    });
  }

  @Override public <T> Observable<String> toJson(T obj) {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        String result = gson.toJson(obj);
        subscriber.onNext(result);
        subscriber.onCompleted();
      }
    });
  }
}
