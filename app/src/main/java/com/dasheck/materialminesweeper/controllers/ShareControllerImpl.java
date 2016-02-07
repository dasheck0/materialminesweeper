package com.dasheck.materialminesweeper.controllers;

import android.content.Context;
import android.content.Intent;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class ShareControllerImpl implements ShareController {

  @Inject Context context;

  @Inject public ShareControllerImpl() {
  }

  @Override public Observable<Void> shareContent(String content) {
    return createShareIntent().map(intent -> {
      intent.putExtra(Intent.EXTRA_TEXT, content);
      context.startActivity(intent);

      return null;
    });
  }

  private Observable<Intent> createShareIntent() {
    return Observable.create(new Observable.OnSubscribe<Intent>() {
      @Override public void call(Subscriber<? super Intent> subscriber) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        subscriber.onNext(intent);
        subscriber.onCompleted();
      }
    });
  }
}
