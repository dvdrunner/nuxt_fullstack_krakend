package ea.ciges.krakend.model;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public abstract class EntidadInfo<E extends Serializable> extends EntidadPK<E> {



    protected LocalDateTime fechaCreacion;

    protected LocalDateTime fechaModificacion;

    protected String usuarioCreacion;

    protected String usuarioModificacion;

    protected Boolean valido = true;


}
