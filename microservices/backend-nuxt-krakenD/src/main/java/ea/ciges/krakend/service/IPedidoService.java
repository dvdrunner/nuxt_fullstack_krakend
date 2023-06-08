package ea.ciges.krakend.service;




import ea.ciges.krakend.model.Pedido;

import java.util.List;

public interface IPedidoService extends IBaseService<Pedido, Long> {

    public int countPages(int size) throws Exception;

    List<Pedido> findAllInOnePage(int page, int size) throws Exception;
}
