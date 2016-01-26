package com.dasheck.materialminesweeper.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by s.neidig on 17/01/16.
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE)
   public @interface Title {
    int value();
}
