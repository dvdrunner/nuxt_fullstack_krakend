package ea.ciges.krakend.mapper;



import ea.ciges.krakend.dto.ProductoDto;
import ea.ciges.krakend.model.Producto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {Producto.class, ProductoDto.class})
public interface ProductoMapper extends MapperGeneric<Producto, ProductoDto>  {





}


