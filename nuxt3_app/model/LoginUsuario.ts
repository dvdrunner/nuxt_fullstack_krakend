export class LoginUsuario {
  private nombreUsuario?: string
  private password?: string

  constructor(nombreUsuario?: string, password?: string) {
    this.nombreUsuario = nombreUsuario
    this.password = password
  }
}
