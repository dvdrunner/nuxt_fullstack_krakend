import { useToast } from 'primevue/usetoast'
import type { LoginUsuario } from '~/model/LoginUsuario'
import type { NuevoUsuario } from '~/model/NuevoUsuario'

export class AuthService {
  authURL = 'http://localhost:8090/auth/'

  public async nuevo(nuevoUsuario: NuevoUsuario): Promise<any> {
    const { data } = await useFetch(`${this.authURL}nuevo`, {
      method: 'POST',
      body: { nuevoUsuario },
    })

    return data
  }

  public async login(loginUsuario: LoginUsuario): Promise<any> {
    const toast = useToast()
    const { data, error } = await useFetch(`${this.authURL}login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginUsuario),
    })

    if (error && error.value !== null) {
      console.log('Error:', error.value.data.mensaje)
      toast.add({
        severity: 'error',
        summary: 'Guardando',
        detail: 'Credenciales incorrectas',
        life: 3000,
      })

      console.log('El token es:', data)
      return data
    }
  }
}
