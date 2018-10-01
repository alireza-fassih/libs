package ir.fassih.datamanagement.entity;


import ir.fassih.datamanagement.test.SomeTrackableEntity;
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
public class TrackableEntityTest {


    @Autowired
    private SomeTrackableRepository repository;

    @Test
    public void fillCreationTime() {
        SomeTrackableEntity save = repository.save(new SomeTrackableEntity());
        Assert.assertNotNull( save.getCreationTime() );
        Assert.assertNotNull( save.getLastModificationTime() );
    }


    @Test
    public void fillLastModificationTime() {
        SomeTrackableEntity save = repository.save(new SomeTrackableEntity());
        save.setLastModificationTime(null);
        repository.saveAndFlush(save);
        Assert.assertNotNull( save.getLastModificationTime() );
    }



}
