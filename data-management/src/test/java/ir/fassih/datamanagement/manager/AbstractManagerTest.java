package ir.fassih.datamanagement.manager;

import ir.fassih.datamanagement.exceptions.EntityNotFoundException;
import ir.fassih.datamanagement.test.SomeTrackableEntity;
import ir.fassih.datamanagement.test.SomeTrackableManager;
import ir.fassih.datamanagement.test.SomeTrackableRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AbstractManagerTest {

    @Autowired
    private SomeTrackableManager manager;

    @Autowired
    private SomeTrackableRepository repository;



    @Test
    public void testLoad_entityExist() {
        SomeTrackableEntity save = repository.save(new SomeTrackableEntity());
        SomeTrackableEntity someTrackableEntity = manager.find(save.getId());
        Assert.assertNotNull(someTrackableEntity);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testLoad_entityNotExist() {
        manager.find(10001L);
    }

}
