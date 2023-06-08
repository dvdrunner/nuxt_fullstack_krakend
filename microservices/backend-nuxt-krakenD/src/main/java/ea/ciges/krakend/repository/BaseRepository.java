package ea.ciges.krakend.repository;



import ea.ciges.krakend.model.EntidadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<E extends EntidadInfo, ID extends Serializable> extends JpaRepository<E, ID> {
    List<E> findByValido(Boolean valido);
}
