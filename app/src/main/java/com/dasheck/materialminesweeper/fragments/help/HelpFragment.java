package com.dasheck.materialminesweeper.fragments.help;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import com.cleveroad.slidingtutorial.CirclePageIndicator;
import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.PresentationPagerFragment;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.materialminesweeper.fragments.help.pages.Help1Fragment;
import com.dasheck.materialminesweeper.fragments.help.pages.Help2Fragment;
import com.dasheck.materialminesweeper.fragments.help.pages.Help3Fragment;
import com.dasheck.materialminesweeper.fragments.help.pages.Help4Fragment;
import com.dasheck.materialminesweeper.fragments.help.pages.Help5Fragment;
import com.dasheck.materialminesweeper.fragments.help.pages.Help6Fragment;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class HelpFragment extends PresentationPagerFragment implements View.OnTouchListener {

  private View skipButton;
  private ViewPager viewPager;
  private CirclePageIndicator circlePageIndicator;
  private Navigator navigator;
  private int currentPage = -1;
  private GestureDetectorCompat gestureDetector;

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    skipButton = view.findViewById(R.id.skipButton);
    skipButton.setOnClickListener(v -> showMenu());

    viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    viewPager.clearOnPageChangeListeners();
    viewPager.addOnPageChangeListener(this);
    viewPager.setCurrentItem(0);
    viewPager.setOnTouchListener(this);

    circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
    circlePageIndicator.setViewPager(viewPager, getPagesCount());

    gestureDetector = new GestureDetectorCompat(getActivity(), new SwipeListener());
  }

  @Override protected int getLayoutResId() {
    return R.layout.fragment_help;
  }

  @Override protected int getViewPagerResId() {
    return R.id.viewPager;
  }

  @Override protected int getIndicatorResId() {
    return R.id.indicator;
  }

  @Override protected int getButtonSkipResId() {
    return R.id.skipButton;
  }

  @Override protected int getPagesCount() {
    return 6;
  }

  @Override protected PageFragment getPage(int position) {
    switch (position) {
      case 0:
        return new Help1Fragment();
      case 1:
        return new Help2Fragment();
      case 2:
        return new Help3Fragment();
      case 3:
        return new Help4Fragment();
      case 4:
        return new Help5Fragment();
      case 5:
        return new Help6Fragment();
    }

    throw new IllegalStateException("Position is invalid");
  }

  @Override protected int getPageColor(int position) {
    return ContextCompat.getColor(getContext(), R.color.lightgray);
  }

  @Override protected boolean isInfiniteScrollEnabled() {
    return false;
  }

  @Override public void onPageSelected(int position) {
    currentPage = position;

    if (position >= getPagesCount()) {

      showMenu();
    }
  }

  public void setNavigator(Navigator navigator) {
    this.navigator = navigator;
  }

  private void showMenu() {
    navigator.showMenu();
  }

  @Override public boolean onTouch(View v, MotionEvent event) {
    if (currentPage == getPagesCount() - 1) {
      this.gestureDetector.onTouchEvent(event);
      return true;
    }

    return false;
  }

  class SwipeListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
      if (velocityX > 0) {
        viewPager.setCurrentItem(getPagesCount() - 2, true);
      }

      return true;
    }
  }
}
