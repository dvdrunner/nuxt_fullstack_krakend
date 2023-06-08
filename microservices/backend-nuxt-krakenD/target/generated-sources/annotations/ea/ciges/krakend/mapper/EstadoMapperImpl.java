package ea.ciges.krakend.mapper;

import ea.ciges.krakend.dto.EstadoDto;
import ea.ciges.krakend.dto.EstadoDto.EstadoDtoBuilder;
import ea.ciges.krakend.model.Estado;
import ea.ciges.krakend.model.Estado.EstadoBuilder;
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
public class EstadoMapperImpl implements EstadoMapper {

    @Override
    public EstadoDto toDto(Estado entity) {
        if ( entity == null ) {
            return null;
        }

        EstadoDtoBuilder estadoDto = EstadoDto.builder();

        estadoDto.id( entity.getId() );
        estadoDto.nombre( entity.getNombre() );

        return estadoDto.build();
    }

    @Override
    public Estado toEntity(EstadoDto dto) {
        if ( dto == null ) {
            return null;
        }

        EstadoBuilder estado = Estado.builder();

        estado.id( dto.getId() );
        estado.nombre( dto.getNombre() );

        return estado.build();
    }

    @Override
    public List<EstadoDto> toDtoList(List<Estado> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EstadoDto> list = new ArrayList<EstadoDto>( entities.size() );
        for ( Estado estado : entities ) {
            list.add( toDto( estado ) );
        }

        return list;
    }

    @Override
    public List<Estado> toEntityList(List<EstadoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Estado> list = new ArrayList<Estado>( dtos.size() );
        for ( EstadoDto estadoDto : dtos ) {
            list.add( toEntity( estadoDto ) );
        }

        return list;
    }
}
