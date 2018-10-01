package ir.fassih.datamanagement.test;

import ir.fassih.datamanagement.manager.AbstractManager;
import org.springframework.stereotype.Component;

@Component
public class SomeTrackableManager extends AbstractManager<SomeTrackableEntity, Long> {

    @Override
    protected Class<SomeTrackableEntity> getEntityClass() {
        return SomeTrackableEntity.class;
    }

}
