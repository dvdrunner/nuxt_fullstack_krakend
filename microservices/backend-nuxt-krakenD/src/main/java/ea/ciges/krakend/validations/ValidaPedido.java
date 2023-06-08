package ea.ciges.krakend.validations;


import ea.ciges.krakend.model.Pedido;
import ea.ciges.krakend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidaPedido extends AbstractValidator<Pedido, ValidaPedido> {

    @Autowired
    private PedidoRepository pedidoRepository;


    public ValidaPedido validarNumSeguimientoRepetido() {
        Pedido pedido = null;
// usar el repositorio para realizar alguna validación
        if(getEntidad().getNumSeguimiento()!=null){
            pedido = pedidoRepository.findByNumSeguimientoAndValido(getEntidad().getNumSeguimiento(), true).orElse(null);
        }
      
        if (pedido != null) {
            getErrores().add("El pedido con ese nº de seguimiento ya existe.");
        }
        return this;
    }


}
