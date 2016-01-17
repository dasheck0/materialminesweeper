package com.dasheck.materialminesweeper.activities;

import com.dasheck.materialminesweeper.fragments.test.DaggerTestComponent;
import com.dasheck.materialminesweeper.fragments.test.TestComponent;
import com.dasheck.materialminesweeper.fragments.test.TestFragment;
import com.dasheck.materialminesweeper.fragments.test.TestModule;
import com.dasheck.materialminesweeper.fragments.test.TestPresenterImpl;
import javax.inject.Inject;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Navigator {

  private BaseActivity baseActivity;

  public Navigator(BaseActivity baseActivity) {
    this.baseActivity = baseActivity;
  }

  public void showTest() {
    TestFragment fragment = new TestFragment();
    TestPresenterImpl presenter = new TestPresenterImpl();

    TestComponent component = DaggerTestComponent.builder()
        .activityComponent(baseActivity.getActivityComponent())
        .testModule(new TestModule(fragment, presenter))
        .build();

    component.inject(fragment);
    component.inject(presenter);

    baseActivity.getSupportFragmentManager()
        .beginTransaction()
        .add(baseActivity.getFragmentContainerId(), fragment, fragment.getClass().getName())
        .addToBackStack(fragment.getClass().getName())
        .commit();
  }
}
