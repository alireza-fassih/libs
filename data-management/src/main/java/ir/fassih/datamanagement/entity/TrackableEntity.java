package ir.fassih.datamanagement.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

public interface TrackableEntity {

    Date getCreationTime();
    void setCreationTime(Date creationTime);


    Date getLastModificationTime();
    void setLastModificationTime(Date lastModificationTime);



    class TrackableEntityListener {

        @PrePersist
        void fillCreationAndLastModificationTime(TrackableEntity entity) {
            entity.setCreationTime(new Date());
            entity.setLastModificationTime(new Date());
        }


        @PreUpdate
        void fillLastModificationTime(TrackableEntity entity) {
            entity.setLastModificationTime(new Date());
        }

    }

}
