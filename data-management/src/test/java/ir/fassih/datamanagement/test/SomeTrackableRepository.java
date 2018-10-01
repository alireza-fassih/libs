package ir.fassih.datamanagement.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SomeTrackableRepository extends JpaRepository<SomeTrackableEntity, Long> {
}
