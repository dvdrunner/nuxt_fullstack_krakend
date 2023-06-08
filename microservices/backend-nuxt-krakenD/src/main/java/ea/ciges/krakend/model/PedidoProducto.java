package ea.ciges.krakend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "PEDIDO_PRODUCTO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProducto extends EntidadInfo<PedidoProductoPK> {

    @EmbeddedId
    private PedidoProductoPK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PEDIDO_PK",referencedColumnName = "ID", insertable = false, updatable = false)
    @JsonIgnore // Ignora la referencia circular a Pedido
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUCTO_PK",referencedColumnName = "ID", insertable = false, updatable = false)
    private Producto producto;
}
