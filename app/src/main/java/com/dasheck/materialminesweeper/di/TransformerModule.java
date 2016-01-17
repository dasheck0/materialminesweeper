package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.transformators.TestTransformer;
import com.dasheck.model.transformators.TestTransformerImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 17/01/16.
 */
@Module public class TransformerModule {

  @Provides @PerActivity TestTransformer provideTestTransformer(
      TestTransformerImpl testTransformer) {
    return testTransformer;
  }
}
