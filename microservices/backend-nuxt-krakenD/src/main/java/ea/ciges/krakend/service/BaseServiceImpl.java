package ea.ciges.krakend.service;




import ea.ciges.krakend.model.EntidadInfo;
import ea.ciges.krakend.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends EntidadInfo, ID extends Serializable> implements IBaseService<E, ID> {

    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public E findById(ID id) throws Exception {
        try {

            // se usa para atrapar un null
            Optional<E> varOptional = baseRepository.findById(id);

            E entity = varOptional.get();

            return entity;

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }

    }

    @Override
    public E save(E entityForm) throws Exception {
        try {

            entityForm = baseRepository.save(entityForm);

            return entityForm;

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    @Override
    public E update(ID id, E entityForm) throws Exception {

        try {
            Optional<E> entityOptional = baseRepository.findById(id);

            E entity = entityOptional.get();

            entity = baseRepository.save(entityForm);

            return entity;

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }


    @Override
    public List<E> findAll() throws Exception {
        try {

            return baseRepository.findByValido(true);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
/*
    @Override
    public Page<D> findAllLazy(int page, int size) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<E> entities = baseRepository.findAll(pageable);
            List<D> dtos = mapper.toDtoList(entities.getContent());
            return new PageImpl<>(dtos, pageable, entities.getTotalElements());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    */

    @Override
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return !baseRepository.existsById(id);
    }


    @Override
    public boolean existsById(ID id) throws Exception {
        try {
            return baseRepository.existsById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public long count() throws Exception {
        try {
            return baseRepository.count();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
