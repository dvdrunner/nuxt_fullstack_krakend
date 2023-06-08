package ea.ciges.krakend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EstadoDto extends EntidadInfoDto implements IDto {
    private Long id;
    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    private String nombre;


}
