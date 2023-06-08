package ea.ciges.krakend.repository;





import ea.ciges.krakend.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {

    Optional<Pedido> findByNumSeguimientoAndValido(String numSeguimiento, Boolean valido);
}
