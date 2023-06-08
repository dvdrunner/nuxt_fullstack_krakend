package ea.ciges.krakend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public abstract class EntidadInfoDto {


    protected LocalDateTime fechaCreacion;

    protected LocalDateTime fechaModificacion;

    protected String usuarioCreacion;

    protected String usuarioModificacion;

    protected Boolean valido = true;


}
