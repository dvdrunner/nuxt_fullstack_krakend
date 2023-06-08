export class Usuario {
  private _nombreCorto: string
  private _fechaCaducaSesion: Date

  constructor(nombreCorto?: string, fechaCaducaSesion?: Date) {
    this._nombreCorto = nombreCorto || ''
    this._fechaCaducaSesion = fechaCaducaSesion || new Date()
  }

  // Getter y Setter para nombreCorto
  get nombreCorto(): string {
    return this._nombreCorto
  }

  set nombreCorto(nuevoNombreCorto: string) {
    this._nombreCorto = nuevoNombreCorto
  }

  // Getter y Setter para fechaCaducaSesion
  get fechaCaducaSesion(): Date {
    return this._fechaCaducaSesion
  }

  set fechaCaducaSesion(nuevaFechaCaducaSesion: Date) {
    this._fechaCaducaSesion = nuevaFechaCaducaSesion
  }
}
