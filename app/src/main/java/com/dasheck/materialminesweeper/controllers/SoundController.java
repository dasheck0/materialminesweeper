package com.dasheck.materialminesweeper.controllers;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface SoundController {

  Observable<Void> registerBackgroundMusic(int resourceId);

  Observable<Boolean> isBackgroundMusicRegistered();

  Observable<Void> startBackgroundMusic();

  Observable<Void> stopBackgroundMusic();

  Observable<Void> setVolume(float volume);

  Observable<Void> registerSoundEffect(int resourceId);

  Observable<Void> playSoundEffect(int index);

  Observable<Void> releaseAll();
}
