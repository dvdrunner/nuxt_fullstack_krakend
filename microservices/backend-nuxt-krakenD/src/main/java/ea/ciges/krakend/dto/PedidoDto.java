package ea.ciges.krakend.dto;

import ea.ciges.krakend.model.Estado;
import ea.ciges.krakend.model.PedidoProducto;
import ea.ciges.krakend.model.Producto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PedidoDto extends EntidadInfoDto implements IDto {

    private Long id;
    private Estado estado;

    private List<Producto> productos = new ArrayList<>();

    private List<PedidoProducto> pedidosProductos = new ArrayList<>();

    @Min(value = 0)
    private double precioTotal;

    @NotBlank(message = "La dirección de envío del pedido no puede estar vacío")
    private String direccionEnvio;

    @NotBlank(message = "El método de pago del pedido no puede estar vacío")
    private String metodoDePago;

    private String numSeguimiento;

    private String observaciones;

    private LocalDateTime fechaPedido;

    private Boolean esPedidoCaro;
}
