export class EntidadInfo {
    protected fechaCreacion?: Date;
    protected fechaModificacion?: Date;
    protected usuarioCreacion?: string;
    protected usuarioModificacion?: string;
    protected valido?: boolean = true;
  
    constructor(
      fechaCreacion?: Date,
      fechaModificacion?: Date,
      usuarioCreacion?: string,
      usuarioModificacion?: string,
      valido?: boolean
    ) {
      this.fechaCreacion = fechaCreacion;
      this.fechaModificacion = fechaModificacion;
      this.usuarioCreacion = usuarioCreacion;
      this.usuarioModificacion = usuarioModificacion;
      this.valido = valido;
    }
  
    toJson(): string {
      return JSON.stringify({
        fechaCreacion: this.fechaCreacion,
        fechaModificacion: this.fechaModificacion,
        usuarioCreacion: this.usuarioCreacion,
        usuarioModificacion: this.usuarioModificacion,
        valido: this.valido,
      });
    }
  }
  