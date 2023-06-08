package ea.ciges.krakend.mapper;



import ea.ciges.krakend.dto.PedidoDto;
import ea.ciges.krakend.model.Pedido;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {Pedido.class, PedidoDto.class})
public interface PedidoMapper extends MapperGeneric<Pedido, PedidoDto> {



}


