package ir.fassih.datamanagement.test;

import ir.fassih.datamanagement.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeTrackableRepository extends AbstractRepository<SomeTrackableEntity, Long> {
}
