package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.ChartValues;
import com.dasheck.model.models.Filter;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.ValueSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public interface HistoryView extends BaseView {

  void setGameInformationList(List<GameInformation> gameInformationList);

  void openFilterDialog(Filter currentFilter);

  void setChartValues(ValueSet valueSet);

  void setChartTypes(List<String> chartTypes);
}
