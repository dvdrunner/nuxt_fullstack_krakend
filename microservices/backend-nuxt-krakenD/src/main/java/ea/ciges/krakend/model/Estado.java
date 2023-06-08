package ea.ciges.krakend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Entity
@Table(name = "ESTADO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado extends EntidadInfo<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;


}
