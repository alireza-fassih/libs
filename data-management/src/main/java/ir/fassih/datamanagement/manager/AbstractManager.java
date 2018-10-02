package ir.fassih.datamanagement.manager;

import ir.fassih.datamanagement.entity.AbstractBaseEntity;
import ir.fassih.datamanagement.exceptions.EntityNotFoundException;
import ir.fassih.datamanagement.repository.AbstractRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;


public abstract class AbstractManager<T extends AbstractBaseEntity, I extends Serializable> {

    protected abstract Class<T> getEntityClass();

    @Setter(onMethod = @__(@Autowired))
    protected AbstractRepository<T, I> repository;


    @Transactional(readOnly = true)
    public T find(I id) {
        Optional<T> entity = repository.findById(id);
        if(!entity.isPresent()) {
            throw new EntityNotFoundException(getEntityClass());
        }
        return entity.get();
    }

    @Transactional
    public void save(T entity) {
        repository.save(entity);
    }

    

}
