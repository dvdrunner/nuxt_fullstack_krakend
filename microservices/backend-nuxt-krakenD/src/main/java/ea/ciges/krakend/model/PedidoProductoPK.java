package ea.ciges.krakend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PedidoProductoPK implements Serializable {

    @Column(name = "ID_PEDIDO_PK", nullable = false, insertable = false, updatable = false)
    private Long idPedido;


    @Column(name = "ID_PRODUCTO_PK", nullable = false, insertable = false, updatable = false)
    private Long idProducto;
}