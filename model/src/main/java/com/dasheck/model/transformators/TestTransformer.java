package com.dasheck.model.transformators;

import com.dasheck.model.entities.TestEntity;
import com.dasheck.model.models.Test;
import java.util.List;
import rx.Observable;

/**
 * Created by s.neidig on 17/01/16.
 */
public interface TestTransformer {

  Observable<Test> toModel(TestEntity testEntity);

  Observable<List<Test>> toModel(List<TestEntity> testEntities);
}
