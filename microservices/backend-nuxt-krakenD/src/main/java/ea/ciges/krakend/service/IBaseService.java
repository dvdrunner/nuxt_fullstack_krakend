package ea.ciges.krakend.service;





import ea.ciges.krakend.model.EntidadInfo;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<E extends EntidadInfo, ID extends Serializable> {

    E findById(ID id) throws Exception;

    E save(E entityForm) throws Exception;

    E update(ID id, E entityForm) throws Exception;

    List<E> findAll() throws Exception;

    boolean delete(ID id) throws Exception;


    boolean existsById(ID id) throws Exception;

    long count() throws Exception;
}