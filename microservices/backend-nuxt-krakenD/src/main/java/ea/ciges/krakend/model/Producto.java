package ea.ciges.krakend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends EntidadInfo<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;


    @NotBlank(message = "La descripción no puede estar vacía.")
    private String descripcion;

    private BigDecimal precio;

    private Boolean enStock;


}
