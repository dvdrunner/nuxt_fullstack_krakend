import { EntidadInfo } from "./EntidadInfo";

export default class Estado extends EntidadInfo {
  private id?: number;

  private nombre?: string;

  constructor(id?: number, nombre?: string) {
    super();
    this.id = id;
    this.nombre = nombre;
  }

  toJson(): string {
    return JSON.stringify({
      id: this.id,
      nombre: this.nombre,
    });
  }

  name(): void {
    let nombre ='paco';
    console.log('Hola', nombre);

  }
}


