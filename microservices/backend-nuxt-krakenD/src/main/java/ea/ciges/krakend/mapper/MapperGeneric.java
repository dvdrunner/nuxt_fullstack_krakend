package ea.ciges.krakend.mapper;

import java.util.List;


public interface MapperGeneric<E, D> {

    D toDto(E entity);
    E toEntity(D dto);

    List<D> toDtoList(List<E> entities);
    List<E> toEntityList(List<D> dtos);
}
