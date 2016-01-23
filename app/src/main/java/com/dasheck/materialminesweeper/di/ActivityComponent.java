package com.dasheck.materialminesweeper.di;

import android.content.Context;
import com.dasheck.materialminesweeper.activities.BaseActivity;
import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.datastores.FieldDatastore;
import dagger.Component;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerActivity @Component(modules = { AcitivityModule.class, TransformerModule.class, DatastoresModule.class }, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

  BaseActivity baseActivity();

  Context context();

  Navigator navigator();

  /* Transformer */

  /* Datastores */

  FieldDatastore fieldDatastore();
}
