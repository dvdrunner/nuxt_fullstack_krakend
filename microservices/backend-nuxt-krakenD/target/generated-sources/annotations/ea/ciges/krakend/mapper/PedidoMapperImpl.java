package ea.ciges.krakend.mapper;

import ea.ciges.krakend.dto.PedidoDto;
import ea.ciges.krakend.dto.PedidoDto.PedidoDtoBuilder;
import ea.ciges.krakend.model.Pedido;
import ea.ciges.krakend.model.Pedido.PedidoBuilder;
import ea.ciges.krakend.model.PedidoProducto;
import ea.ciges.krakend.model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T13:57:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class PedidoMapperImpl implements PedidoMapper {

    @Override
    public PedidoDto toDto(Pedido entity) {
        if ( entity == null ) {
            return null;
        }

        PedidoDtoBuilder pedidoDto = PedidoDto.builder();

        pedidoDto.id( entity.getId() );
        pedidoDto.estado( entity.getEstado() );
        List<Producto> list = entity.getProductos();
        if ( list != null ) {
            pedidoDto.productos( new ArrayList<Producto>( list ) );
        }
        List<PedidoProducto> list1 = entity.getPedidosProductos();
        if ( list1 != null ) {
            pedidoDto.pedidosProductos( new ArrayList<PedidoProducto>( list1 ) );
        }
        pedidoDto.precioTotal( entity.getPrecioTotal() );
        pedidoDto.direccionEnvio( entity.getDireccionEnvio() );
        pedidoDto.metodoDePago( entity.getMetodoDePago() );
        pedidoDto.numSeguimiento( entity.getNumSeguimiento() );
        pedidoDto.observaciones( entity.getObservaciones() );
        pedidoDto.fechaPedido( entity.getFechaPedido() );
        pedidoDto.esPedidoCaro( entity.getEsPedidoCaro() );

        return pedidoDto.build();
    }

    @Override
    public Pedido toEntity(PedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        PedidoBuilder pedido = Pedido.builder();

        pedido.id( dto.getId() );
        pedido.estado( dto.getEstado() );
        List<Producto> list = dto.getProductos();
        if ( list != null ) {
            pedido.productos( new ArrayList<Producto>( list ) );
        }
        List<PedidoProducto> list1 = dto.getPedidosProductos();
        if ( list1 != null ) {
            pedido.pedidosProductos( new ArrayList<PedidoProducto>( list1 ) );
        }
        pedido.precioTotal( dto.getPrecioTotal() );
        pedido.direccionEnvio( dto.getDireccionEnvio() );
        pedido.metodoDePago( dto.getMetodoDePago() );
        pedido.numSeguimiento( dto.getNumSeguimiento() );
        pedido.observaciones( dto.getObservaciones() );
        pedido.fechaPedido( dto.getFechaPedido() );
        pedido.esPedidoCaro( dto.getEsPedidoCaro() );

        return pedido.build();
    }

    @Override
    public List<PedidoDto> toDtoList(List<Pedido> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PedidoDto> list = new ArrayList<PedidoDto>( entities.size() );
        for ( Pedido pedido : entities ) {
            list.add( toDto( pedido ) );
        }

        return list;
    }

    @Override
    public List<Pedido> toEntityList(List<PedidoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Pedido> list = new ArrayList<Pedido>( dtos.size() );
        for ( PedidoDto pedidoDto : dtos ) {
            list.add( toEntity( pedidoDto ) );
        }

        return list;
    }
}
