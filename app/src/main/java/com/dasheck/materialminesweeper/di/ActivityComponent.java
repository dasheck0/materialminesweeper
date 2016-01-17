package com.dasheck.materialminesweeper.di;

import android.content.Context;

import com.dasheck.materialminesweeper.activities.BaseActivity;
import com.dasheck.materialminesweeper.annotations.PerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerActivity
@Component(modules = AcitivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    BaseActivity baseActivity();

    Context context();
}
