package ea.ciges.krakend.controller;


import ea.ciges.krakend.anotaciones.BeforeInsert;
import ea.ciges.krakend.dto.PedidoDto;
import ea.ciges.krakend.mapper.PedidoMapper;
import ea.ciges.krakend.model.Pedido;
import ea.ciges.krakend.service.PedidoServiceImpl;
import ea.ciges.krakend.validations.ValidaPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pedido")
//@RolesAllowed({"product_read"})
//Abarca la clase en general, despues cada metodo puede especificar que rol tiene permiso
@Setter
@Getter
public class PedidoController extends GenericControllerImpl<Pedido, Long, PedidoDto, PedidoServiceImpl, PedidoMapper> {
    @Autowired
    private ValidaPedido validaPedido;


    @BeforeInsert
    public void beforeInsert() {

        setListaErrores(validaPedido.valida(getEntity()).validarNumSeguimientoRepetido().todo());

    }


}
