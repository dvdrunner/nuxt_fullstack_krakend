import { EntidadInfo } from "./EntidadInfo";
import Estado from "./Estado";
import Producto from "./Producto";

export default class Pedido extends EntidadInfo{
  id?: number;
  estado?: Estado;
  productos: Producto[] = [];
  pedidosProductos: any[] = [];
  precioTotal?: number;
  direccionEnvio?: string;
  metodoDePago?: string;
  numSeguimiento?: string;
  observaciones?: string;
  fechaPedido?: Date;
  esPedidoCaro?: boolean;

  constructor(
    id?: number,
    estado?: Estado,
    productos: Producto[] = [], // se asigna un array vacío como valor por defecto
    pedidosProductos: any[] = [], // se asigna un array vacío como valor por defecto
    precioTotal?: number,
    direccionEnvio?: string,
    metodoDePago?: string,
    numSeguimiento?: string,
    observaciones?: string,
    fechaPedido?: Date,
    esPedidoCaro?: boolean
  ) {
    super();
    this.id = id;
    this.estado = estado;
    this.productos = productos;
    this.pedidosProductos = pedidosProductos;
    this.precioTotal = precioTotal;
    this.direccionEnvio = direccionEnvio;
    this.metodoDePago = metodoDePago;
    this.numSeguimiento = numSeguimiento;
    this.observaciones = observaciones;
    this.fechaPedido = fechaPedido;
    this.esPedidoCaro = esPedidoCaro;
  }



  // método para convertir la instancia en un objeto que pueda ser stringificado
  toJSON() {
    return {
      id: this.id,
      estado: this.estado,
      productos: this.productos,
      pedidosProductos: this.pedidosProductos,
      precioTotal: this.precioTotal,
      direccionEnvio: this.direccionEnvio,
      metodoDePago: this.metodoDePago,
      numSeguimiento: this.numSeguimiento,
      observaciones: this.observaciones,
      fechaPedido: this.fechaPedido,
      esPedidoCaro: this.esPedidoCaro,
    };
  }
}
