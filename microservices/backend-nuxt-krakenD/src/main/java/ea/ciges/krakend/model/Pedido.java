package ea.ciges.krakend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Número de pedido: un identificador único para cada pedido que se realiza.
 * Fecha de pedido: la fecha en que se realizó el pedido.
 * Estado del pedido: el estado actual del pedido (por ejemplo, pendiente, en proceso, enviado, entregado, cancelado, etc.).
 * Cliente: el nombre o la identificación del cliente que realizó el pedido.
 * Productos: una lista de los productos que se incluyeron en el pedido, junto con su cantidad y precio.
 * Total: el monto total del pedido, incluyendo impuestos y gastos de envío si los hubiera.
 * Dirección de envío: la dirección donde se entregará el pedido.
 * Método de pago: la forma en que el cliente pagó el pedido (por ejemplo, tarjeta de crédito, transferencia bancaria, pago en efectivo, etc.).
 * Número de seguimiento: si se proporciona un número de seguimiento para el envío del pedido, podrías incluirlo en la tabla.
 * Notas: cualquier nota adicional que el cliente haya proporcionado al realizar el pedido o que el personal de atención al cliente haya registrado posteriormente.
 */
@Entity
@Table(name = "PEDIDO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends EntidadInfo<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_PK")
    private Estado estado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PEDIDO_PRODUCTO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO_PK"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUCTO_PK"))
    private List<Producto> productos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "pedido")
    private List<PedidoProducto> pedidosProductos = new ArrayList<>();

    @Column(nullable = false)
    @Min(value = 0)
    private double precioTotal;

    @NotBlank
    private String direccionEnvio;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodoDePago;

    @Column(unique = true, length = 50)
    private String numSeguimiento;

    private String observaciones;

    private LocalDateTime fechaPedido;

    private Boolean esPedidoCaro;


}
