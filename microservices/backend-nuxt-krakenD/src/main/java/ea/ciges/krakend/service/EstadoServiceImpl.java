package ea.ciges.krakend.service;



import ea.ciges.krakend.model.Estado;
import ea.ciges.krakend.repository.BaseRepository;
import ea.ciges.krakend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoServiceImpl extends BaseServiceImpl<Estado, Long>  {

    @Autowired
    private PedidoRepository pedidoRepository;

    public EstadoServiceImpl(BaseRepository<Estado, Long> baseRepository) {
        super(baseRepository);
    }





}