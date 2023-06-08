package ea.ciges.krakend.repository;





import ea.ciges.krakend.model.Pedido;
import ea.ciges.krakend.model.PedidoProducto;
import ea.ciges.krakend.model.PedidoProductoPK;
import ea.ciges.krakend.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PedidoProductoRepository extends BaseRepository<PedidoProducto, PedidoProductoPK> {

    List<PedidoProducto> findByPedidoAndValido(Pedido pedido, Boolean valido);

    List<PedidoProducto> findByProductoAndValido(Producto producto, Boolean valido);
}
