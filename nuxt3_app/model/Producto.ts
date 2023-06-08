import { EntidadInfo } from "./EntidadInfo";

export default class Producto extends EntidadInfo{
    private id?: number;
    private nombre?: string;
    private descripcion?: string;
    private precio?: number;
    private enStock?: boolean;

    constructor(
        id?: number,
        nombre?: string,
        descripcion?: string,
        precio?: number,
        enStock?: boolean
    ) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.enStock = enStock;
    }

    toJson(): string {
        return JSON.stringify({
            id: this.id,
            nombre: this.nombre,
            descripcion: this.descripcion,
            precio: this.precio,
            enStock: this.enStock,
        });
    }
}
