package com.dasheck.materialminesweeper.activities;

import android.os.Bundle;

import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.annotations.Layout;
import java.util.Arrays;
import timber.log.Timber;

@Layout(R.layout.activity_main) public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivityComponent().navigator().showMenu();
  }
}