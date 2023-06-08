package ea.ciges.krakend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductoDto extends EntidadInfoDto implements IDto {
    private Long id;
    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    private String nombre;
    @NotBlank(message = "La descripción del producto no puede estar vacía.")
    private String descripcion;
    private BigDecimal precio;
    private Boolean enStock;

}
