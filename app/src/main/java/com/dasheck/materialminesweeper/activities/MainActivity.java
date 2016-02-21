package com.dasheck.materialminesweeper.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.di.ActivityComponent;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.materialminesweeper.fragments.help.HelpFragment;
import com.dasheck.materialminesweeper.fragments.history.HistoryFragment;
import com.dasheck.materialminesweeper.fragments.menu.MenuFragment;
import com.dasheck.materialminesweeper.fragments.profile.ProfileFragment;
import com.dasheck.materialminesweeper.fragments.settings.SettingsFragment;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.User;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import timber.log.Timber;

@Layout(R.layout.activity_main) public class MainActivity extends BaseActivity
    implements View.OnClickListener, TextWatcher {

  private static final int REQUEST_CODE_IMAGE = 100;

  @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
  @Bind(R.id.navigationView) NavigationView navigationView;

  private ImageView profileImage;
  private EditText usernameEditText;

  private ActivityPresenter presenter;

  private User user;
  private Timer timer;

  private ActionBarDrawerToggle actionBarDrawerToggle;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setupNavigationItemListener();
    getActivityComponent().settingsDatastore().isFirstStart().subscribe(isFirstStart -> {
      if (isFirstStart) {
        getActivityComponent().navigator().showHelp();
        getActivityComponent().settingsDatastore().setFirstStart(false).subscribe();
      } else {
        getActivityComponent().navigator().showMenu();
      }
    });

    usernameEditText = (EditText) navigationView.getHeaderView(0).findViewById(R.id.usernameEditText);
    usernameEditText.addTextChangedListener(this);

    profileImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profileImageView);
    ImageView profileButton = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.updateAccountButton);
    profileButton.setOnClickListener(this);

    presenter = new ActivityPresenter(this, getActivityComponent());

    timer = new Timer();
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    getActivityComponent().soundController().releaseAll().subscribe(x -> Timber.d("MainActivity:43: " + "bye"));
  }

  public void setupDrawerLayout(Toolbar toolbar) {
    if (toolbar == null) {
      actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {
        @Override public void onDrawerClosed(View drawerView) {
          super.onDrawerClosed(drawerView);
          MainActivity.this.onDrawerClosed(drawerView);
        }
      };
    } else {
      actionBarDrawerToggle =
          new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override public void onDrawerClosed(View drawerView) {
              super.onDrawerClosed(drawerView);
              MainActivity.this.onDrawerClosed(drawerView);
            }
          };
    }

    drawerLayout.setDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();
  }

  private void onDrawerClosed(View drawerView) {
    Utilities.hideSoftKeyboard(this, usernameEditText);
  }

  private void setupNavigationItemListener() {
    navigationView.setNavigationItemSelectedListener(item -> {
      if (item.isCheckable()) {
        item.setChecked(true);
      }

      boolean result = false;

      switch (item.getItemId()) {
        case R.id.ic_menu:
          if (!(getCurrentFragment() instanceof MenuFragment)) {
            getNavigator().showMenu();
            result = true;
          }
          break;

        case R.id.ic_leaderbord:
          Timber.d("MenuFragment:69: " + "loading leaderboard");
          result = true;
          break;

        case R.id.ic_history:
          if (!(getCurrentFragment() instanceof HistoryFragment)) {
            getNavigator().showHistory();
            result = true;
          }
          break;

        case R.id.ic_settings:
          if (!(getCurrentFragment() instanceof SettingsFragment)) {
            getNavigator().showSettings();
            result = true;
          }
          break;

        case R.id.ic_help:
          if (!(getCurrentFragment() instanceof HelpFragment)) {
            getNavigator().showHelp();
            result = true;
          }
      }

      if (result) {
        drawerLayout.closeDrawers();
      }

      return result;
    });
  }

  @Override public void onBackPressed() {
    if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
      drawerLayout.closeDrawers();
    } else {
      if (getSupportFragmentManager().getBackStackEntryCount() == 1 || (getCurrentFragment() instanceof MenuFragment)) {
        finish();
      } else {
        Fragment fragment = getCurrentFragment();
        if (fragment != null) {
          if (fragment instanceof BaseFragment) {
            if (!((BaseFragment) fragment).onBackPressed()) {
              super.onBackPressed();
            }
          } else {
            super.onBackPressed();
          }
        }
      }
    }
  }

  private Fragment getCurrentFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();

    if (fragmentManager.getBackStackEntryCount() > 0) {
      String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
      return getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }

    return null;
  }

  @Override public void onClick(View v) {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    startActivityForResult(intent, REQUEST_CODE_IMAGE);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK) {
      try {
        InputStream stream = getContentResolver().openInputStream(data.getData());
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        stream.close();

        presenter.setImageFromBitmap(bitmap);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  public void setDeviceUser(User user) {
    usernameEditText.setText(user.getName());
    Utilities.setBase64Image(profileImage, user.getImage());
  }

  @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
    if (timer != null) {
      timer.cancel();
    }
  }

  @Override public void afterTextChanged(Editable s) {
    timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override public void run() {
        presenter.setUsername(s.toString());
      }
    }, 500);
  }

  public void setProfileImage(Bitmap bitmap) {
    profileImage.setImageBitmap(bitmap);
  }

  public void showUserUpdatedMessage() {
    //runOnUiThread(() -> Toast.makeText(MainActivity.this, "Updated user", Toast.LENGTH_SHORT).show());
  }
}