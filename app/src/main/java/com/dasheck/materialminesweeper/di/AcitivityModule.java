package com.dasheck.materialminesweeper.di;

import android.content.Context;

import com.dasheck.materialminesweeper.activities.BaseActivity;
import com.dasheck.materialminesweeper.annotations.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 17/01/16.
 */
@Module
public class AcitivityModule {

    private BaseActivity baseActivity;

    public AcitivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @PerActivity
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }

    @Provides
    @PerActivity
    Context provideContext() {
        return baseActivity;
    }
}
