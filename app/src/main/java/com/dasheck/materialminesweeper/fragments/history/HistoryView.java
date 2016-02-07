package com.dasheck.materialminesweeper.fragments.history;

import com.dasheck.materialminesweeper.fragments.BaseView;
import com.dasheck.model.models.GameInformation;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public interface HistoryView extends BaseView {

  void setGameInformationList(List<GameInformation> gameInformationList);
}
