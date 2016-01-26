package com.dasheck.materialminesweeper.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.dasheck.materialminesweeper.activities.BaseActivity;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import java.lang.annotation.Annotation;

/**
 * Created by s.neidig on 17/01/16.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

  private BasePresenter presenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupToolbar();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResId(), container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override public void onResume() {
    super.onResume();
    initializeViews();
    if (presenter != null) {
      presenter.onResume();
    }

    ActionBar toolbar = getBaseActivity().getSupportActionBar();
    if(toolbar != null) {
      toolbar.setTitle(getTitle());
    }
  }

  @Override public void setupToolbar() {
    if (presenter != null) {
      presenter.onStop();
    }
  }

  public void setPresenter(BasePresenter presenter) {
    this.presenter = presenter;
  }

  private int getLayoutResId() {
    Annotation annotation = this.getClass().getAnnotation(Layout.class);
    if (annotation != null) {
      return ((Layout) annotation).value();
    } else {
      throw new IllegalStateException("You must provide a layout via the @Layout annotation");
    }
  }

  private String getTitle() {
    Annotation annotation = this.getClass().getAnnotation(Title.class);
    if(annotation != null) {
      int stringResId = ((Title) annotation).value();
      return getContext().getString(stringResId);
    }

    return "";
  }

  private BaseActivity getBaseActivity() {
    return (BaseActivity) getActivity();
  }
}
