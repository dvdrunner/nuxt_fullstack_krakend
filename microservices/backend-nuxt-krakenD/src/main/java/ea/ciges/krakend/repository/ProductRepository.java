package ea.ciges.krakend.repository;




import ea.ciges.krakend.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends BaseRepository<Producto, Long> {

    Optional<Producto> findByNombreAndValido(String nombre, Boolean valido);
}
