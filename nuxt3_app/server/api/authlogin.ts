import axios from 'axios'
import type { JwtDTO } from '~/model/JwtDTO'
import type { LoginUsuario } from '~/model/LoginUsuario'
import type { NuevoUsuario } from '~/model/NuevoUsuario'

export class AuthLogin {
  authURL = 'http://localhost:8090/auth/'
  roles: Array<string> = []

  public async createUser(nuevoUsuario: NuevoUsuario): Promise<any> {
    try {
      const response = await axios.post(`${this.authURL}nuevo`, {
        nuevoUsuario,
      })
      console.log('El nuevo usuario es:', response.data)
      return response.data
    }
    catch (error) {
      console.log('Error al crear nuevo usuario:', error)
      return null
    }
  }

  public async generateToken(loginUsuario: LoginUsuario): Promise<any> {
    console.log('****AUTHLOGIN!!!!******')
    try {
      const response = await axios.post(`${this.authURL}login`, loginUsuario, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      console.log('El token en authlogin.ts es:', response.data)
      return { data: response.data }
    }
    catch (error: any) {
      console.log('Error en authlogin.ts es:', error.response.data.mensaje)
      return { error: error.response.data.mensaje }
    }
  }

  public async refreshToken(jwtDTO: JwtDTO): Promise<any> {
    console.log('REFRESCANDO AUTHLOGIN!!!!!!!!!!!!')
    try {
      const response = await axios.post(`${this.authURL}refresh`, jwtDTO, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      const token = response.data.token
      return token
    }
    catch (error: any) {
      console.log('Error:', error.response.data.mensaje)
    }
  }

  // public async getRoles(): Promise<any> {
  //   try {
  //     const response = await axios.get(`${this.authURL}roles`)
  //     this.roles = response.data
  //     return this.roles
  //   }
  //   catch (error: any) {
  //     console.log('Error:', error.response.data.mensaje)
  //   }
  // }
}
