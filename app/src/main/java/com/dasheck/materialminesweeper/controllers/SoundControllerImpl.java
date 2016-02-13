package com.dasheck.materialminesweeper.controllers;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import com.dasheck.materialminesweeper.activities.BaseActivity;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class SoundControllerImpl implements SoundController {

  private static final int MAX_SOUND_COUNT = 1;

  @Inject BaseActivity baseActivity;
  private MediaPlayer bgmPlayer;
  private SoundPool soundPool;
  private float volume;
  private int[] soundIds;

  @Inject public SoundControllerImpl() {
    this.bgmPlayer = null;
    this.soundPool = new SoundPool(MAX_SOUND_COUNT, AudioManager.STREAM_MUSIC, 0);
    this.soundIds = new int[MAX_SOUND_COUNT];
    this.volume = -1;

    for (int i = 0; i < MAX_SOUND_COUNT; i++) {
      soundIds[i] = -1;
    }
  }

  @Override public Observable<Void> registerBackgroundMusic(int resourceId) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        if (bgmPlayer != null) {
          bgmPlayer.reset();
        }

        bgmPlayer = MediaPlayer.create(baseActivity.getApplicationContext(), resourceId);
        bgmPlayer.setLooping(true);

        if (volume >= 0.0f) {
          bgmPlayer.setVolume(volume, volume);
        }

        {
          subscriber.onNext(null);
        }
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Boolean> isBackgroundMusicRegistered() {
    return Observable.just(bgmPlayer != null);
  }

  @Override public Observable<Void> startBackgroundMusic() {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        if (bgmPlayer != null) {
          bgmPlayer.start();
        }

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Void> stopBackgroundMusic() {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        if (bgmPlayer != null) {
          bgmPlayer.pause();
          bgmPlayer.seekTo(0);
        }

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Void> setVolume(float volume) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        if (bgmPlayer != null) {
          bgmPlayer.setVolume(volume, volume);
          SoundControllerImpl.this.volume = volume;
        }

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Void> registerSoundEffect(int resourceId) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        for (int i = 0; i < soundIds.length; i++) {
          if (soundIds[i] == -1) {
            soundIds[i] = resourceId;
            break;
          }
        }

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Void> playSoundEffect(int index) {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        MediaPlayer sfxPlayer = MediaPlayer.create(baseActivity.getApplicationContext(), index);
        sfxPlayer.setLooping(false);
        sfxPlayer.setVolume(1.0f, 1.0f);
        sfxPlayer.start();
        sfxPlayer.setOnCompletionListener(MediaPlayer::release);

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Void> releaseAll() {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        if (bgmPlayer != null) {
          bgmPlayer.stop();
          bgmPlayer.release();

          Timber.d("SoundControllerImpl:46: " + "Releasing sound controller");
        }

        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }
}
