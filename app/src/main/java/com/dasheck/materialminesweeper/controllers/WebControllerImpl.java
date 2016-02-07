package com.dasheck.materialminesweeper.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class WebControllerImpl implements WebController {

  @Inject Context context;

  @Inject public WebControllerImpl() {
  }

  @Override public Observable<Void> openWebsite(String url) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
            Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }
}
