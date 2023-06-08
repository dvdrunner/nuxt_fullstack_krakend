package ea.ciges.krakend.service;



import ea.ciges.krakend.model.Producto;
import ea.ciges.krakend.repository.BaseRepository;
import ea.ciges.krakend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Producto, Long> implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    public int countPages(int size) throws Exception {
        try {
            Pageable pageable = PageRequest.of(0, size);
            return baseRepository.findAll(pageable).getTotalPages();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<Producto> findAllInOnePage(int page, int size) throws Exception {

        try {
            Pageable pageable = PageRequest.of(page, size);
            return baseRepository.findAll(pageable).getContent();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }


}