package com.dasheck.materialminesweeper.fragments.history;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import butterknife.Bind;
import butterknife.BindColor;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.dasheck.materialminesweeper.R;
import com.dasheck.materialminesweeper.adapters.GameInformationListAdapter;
import com.dasheck.materialminesweeper.annotations.Layout;
import com.dasheck.materialminesweeper.annotations.Title;
import com.dasheck.materialminesweeper.fragments.BaseFragment;
import com.dasheck.materialminesweeper.utilities.Utilities;
import com.dasheck.model.models.ChartValues;
import com.dasheck.model.models.Filter;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.ValueSet;
import com.db.chart.model.LineSet;
import com.db.chart.view.ChartView;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.SineEase;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
@Layout(R.layout.fragment_history) @Title(R.string.history_title) public class HistoryFragment extends BaseFragment
    implements HistoryView, GameInformationListAdapter.OnShareItemClickedListener {

  @Bind(R.id.gameInformationList) RecyclerView gameInformationList;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.lineChart) LineChartView lineChart;

  @BindColor(R.color.colorHintText) int colorHintText;
  @BindColor(R.color.colorAccent) int colorAccent;

  @Inject HistoryPresenter presenter;
  @Inject GameInformationListAdapter adapter;

  private MaterialDialog filterDialog;
  private View customView;

  private CheckBox wonCheckbox;
  private CheckBox lostCheckbox;
  private CheckBox easyCheckbox;
  private CheckBox mediumCheckbox;
  private CheckBox hardCheckbox;
  private CheckBox expertCheckbox;

  @Override public void initializeViews() {
    setPresenter(presenter);
    setHasOptionsMenu(true);

    if (gameInformationList.getAdapter() == null) {
      gameInformationList.setHasFixedSize(true);
      gameInformationList.setLayoutManager(new LinearLayoutManager(getContext()));
      gameInformationList.setAdapter(adapter);
      gameInformationList.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));
      adapter.setOnShareItemClickedListener(this);
    }

    getBaseActivity().setSupportActionBar(toolbar);

    initializeChartView();

    Animation animation = new Animation();

    animation.setDuration(300);
    animation.setEasing(new SineEase());
    animation.setStartPoint(0.0f, 0.0f);
    animation.setAlpha(150);

    /*LineSet dataSet =
        new LineSet(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" },
            new float[] { 0.0f, 1.0f, 4.0f, 10.0f, 2.0f, 14.0f, 2.5f, 1.0f, -1.0f, 20.0f, 0.0f, 1.0f });

    dataSet.setDotsColor(colorAccent);
    dataSet.setDotsRadius(Utilities.convertDpToPixel(4, getContext()));
    dataSet.setSmooth(true);
    dataSet.setColor(colorHintText);

    lineChart.addData(dataSet);
    lineChart.show(new Animation(300));*/
  }

  private void initializeChartView() {
    // Axis
    lineChart.setAxisThickness(Utilities.convertDpToPixel(2, getContext()));
    lineChart.setAxisLabelsSpacing(Utilities.convertDpToPixel(8, getContext()));
    lineChart.setStep(5);

    //Grid
    Paint gridPaint = new Paint(colorHintText);
    gridPaint.setStyle(Paint.Style.STROKE);
    gridPaint.setPathEffect(new DashPathEffect(new float[] { 10, 10 }, 0));
    lineChart.setGrid(ChartView.GridType.HORIZONTAL, gridPaint);

    //Labels
    lineChart.setLabelsColor(colorHintText);

    //Tooltips // TODO: 08/02/16  Add tool tips
  }

  @Override public void setupToolbar() {
    super.setupToolbar();
    getBaseActivity().setupDrawerLayout(toolbar);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.history, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.ic_charts:
        Timber.d("HistoryFragment:60: " + "charts");
        return true;

      case R.id.ic_filter:
        presenter.openFilterDialog();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void setGameInformationList(List<GameInformation> gameInformationList) {
    adapter.clear();
    adapter.addAll(gameInformationList);
    adapter.getHighscore();
  }

  @Override public void openFilterDialog(Filter currentFilter) {
    if (filterDialog == null) {
      initializeFilterDialog();
    }

    wonCheckbox.setChecked(currentFilter.isIncludeWonGames());
    lostCheckbox.setChecked(currentFilter.isIncludeLostGames());
    easyCheckbox.setChecked(currentFilter.isIncludeEasyGames());
    mediumCheckbox.setChecked(currentFilter.isIncludeMediumGames());
    hardCheckbox.setChecked(currentFilter.isIncludeHardGames());
    expertCheckbox.setChecked(currentFilter.isIncludeExpertGames());

    filterDialog.show();
  }

  @Override public void setChartValues(ChartValues chartValues) {
    for (ValueSet valueSet : chartValues.getValueSets()) {

      String[] keyArray = new String[valueSet.getXValues().size()];
      valueSet.getXValues().toArray(keyArray);

      float[] valueArray = new float[valueSet.getYValues().size()];
      for (int i = 0; i < valueSet.getYValues().size(); i++) {
        valueArray[i] = valueSet.getYValues().get(i);
      }

      LineSet dataSet = new LineSet(keyArray, valueArray);

      dataSet.setDotsColor(colorAccent);
      dataSet.setDotsRadius(Utilities.convertDpToPixel(4, getContext()));
      dataSet.setSmooth(true);
      dataSet.setColor(colorHintText);

      lineChart.addData(dataSet);
      lineChart.setAxisBorderValues((int) valueSet.getMinValue(), (int) valueSet.getMaxValue());
      lineChart.show(new Animation(300));
    }
  }

  private void initializeFilterDialog() {
    filterDialog = new MaterialDialog.Builder(getContext()).title("Filter")
        .theme(Theme.LIGHT)
        .customView(R.layout.dialog_filter, true)
        .positiveText("Apply")
        .negativeText("Cancel")
        .neutralText("Reset")
        .onPositive((dialog, which) -> {
          presenter.applyFilter(new Filter(wonCheckbox.isChecked(), lostCheckbox.isChecked(), easyCheckbox.isChecked(),
              mediumCheckbox.isChecked(), hardCheckbox.isChecked(), expertCheckbox.isChecked()));
        })
        .onNeutral((dialog, which) -> presenter.applyInitialFilter())
        .build();

    customView = filterDialog.getCustomView();

    wonCheckbox = (CheckBox) customView.findViewById(R.id.wonCheckbox);
    lostCheckbox = (CheckBox) customView.findViewById(R.id.lostCheckbox);
    easyCheckbox = (CheckBox) customView.findViewById(R.id.easyCheckbox);
    mediumCheckbox = (CheckBox) customView.findViewById(R.id.mediumCheckbox);
    hardCheckbox = (CheckBox) customView.findViewById(R.id.hardCheckbox);
    expertCheckbox = (CheckBox) customView.findViewById(R.id.expertCheckbox);
  }

  @Override public void onShareItemClicked(int position) {
    Timber.d("HistoryFragment:54: " + "Shared item at position " + position);
  }
}
