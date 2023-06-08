package ea.ciges.krakend.mapper;


import ea.ciges.krakend.dto.EstadoDto;
import ea.ciges.krakend.model.Estado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Estado.class, EstadoDto.class})
public interface EstadoMapper extends MapperGeneric<Estado, EstadoDto> {
}


