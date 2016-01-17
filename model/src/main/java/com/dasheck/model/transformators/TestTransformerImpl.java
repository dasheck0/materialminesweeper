package com.dasheck.model.transformators;

import com.dasheck.model.entities.TestEntity;
import com.dasheck.model.models.Test;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 17/01/16.
 */
public class TestTransformerImpl implements TestTransformer {

  @Inject public TestTransformerImpl() {
  }

  @Override public Observable<Test> toModel(TestEntity testEntity) {
    return Observable.just(new Test(Integer.valueOf(testEntity.getId())));
  }

  @Override public Observable<List<Test>> toModel(List<TestEntity> testEntities) {
    return Observable.from(testEntities).flatMap(this::toModel).toList();
  }
}
