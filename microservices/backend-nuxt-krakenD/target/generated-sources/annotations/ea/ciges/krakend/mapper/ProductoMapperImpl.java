package ea.ciges.krakend.mapper;

import ea.ciges.krakend.dto.ProductoDto;
import ea.ciges.krakend.dto.ProductoDto.ProductoDtoBuilder;
import ea.ciges.krakend.model.Producto;
import ea.ciges.krakend.model.Producto.ProductoBuilder;
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
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDto toDto(Producto entity) {
        if ( entity == null ) {
            return null;
        }

        ProductoDtoBuilder productoDto = ProductoDto.builder();

        productoDto.id( entity.getId() );
        productoDto.nombre( entity.getNombre() );
        productoDto.descripcion( entity.getDescripcion() );
        productoDto.precio( entity.getPrecio() );
        productoDto.enStock( entity.getEnStock() );

        return productoDto.build();
    }

    @Override
    public Producto toEntity(ProductoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductoBuilder producto = Producto.builder();

        producto.id( dto.getId() );
        producto.nombre( dto.getNombre() );
        producto.descripcion( dto.getDescripcion() );
        producto.precio( dto.getPrecio() );
        producto.enStock( dto.getEnStock() );

        return producto.build();
    }

    @Override
    public List<ProductoDto> toDtoList(List<Producto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProductoDto> list = new ArrayList<ProductoDto>( entities.size() );
        for ( Producto producto : entities ) {
            list.add( toDto( producto ) );
        }

        return list;
    }

    @Override
    public List<Producto> toEntityList(List<ProductoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Producto> list = new ArrayList<Producto>( dtos.size() );
        for ( ProductoDto productoDto : dtos ) {
            list.add( toEntity( productoDto ) );
        }

        return list;
    }
}
