package com.dasheck.materialminesweeper.activities;

import android.graphics.Bitmap;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.User;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class ActivityPresenter {

  private MainActivity view;
  private ActivityComponent activityComponent;
  private User user;

  public ActivityPresenter(MainActivity view, ActivityComponent activityComponent) {
    this.view = view;
    this.activityComponent = activityComponent;
  }

  public void setDeviceUser(User user) {
    if (user != null) {
      this.user = user;
      view.setDeviceUser(user);
    }
  }

  public void onResume() {
    activityComponent.soundController().startBackgroundMusic().subscribe();
    activityComponent.settingsDatastore().getDeviceUser().subscribe(this::setDeviceUser);
  }

  public void onStop() {
    activityComponent.soundController().stopBackgroundMusic().subscribe();
  }

  public void setImageFromBitmap(Bitmap bitmap) {
    Bitmap resizedImage = Utilities.resizeBitmap(100, 100, bitmap);

    view.setProfileImage(bitmap);
    user.setImage(Utilities.bitmapToBase64String(resizedImage));

    activityComponent.settingsDatastore().updateDeviceUser(user).subscribe();
  }

  public void setUsername(String username) {
    if (username != null && username.length() > 0) {
      user.setName(username);
      activityComponent.settingsDatastore().updateDeviceUser(user).subscribe(x -> view.showUserUpdatedMessage());
    }
  }
}
