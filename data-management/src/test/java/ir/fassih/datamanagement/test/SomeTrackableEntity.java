package ir.fassih.datamanagement.test;

import ir.fassih.datamanagement.entity.AbstractBaseEntity;
import ir.fassih.datamanagement.entity.TrackableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SomeTrackableEntity extends AbstractBaseEntity implements TrackableEntity {

    @Column
    private Date creationTime;

    @Column
    private Date lastModificationTime;


}
