package ea.ciges.krakend.service;







import ea.ciges.krakend.model.Producto;

import java.util.List;

public interface IProductService extends IBaseService<Producto, Long> {

    public int countPages(int size) throws Exception;

    List<Producto> findAllInOnePage(int page, int size) throws Exception;
}
