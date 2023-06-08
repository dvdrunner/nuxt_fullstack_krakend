import { AuthLogin } from './authlogin'
import type { LoginUsuario } from '~/model/LoginUsuario'
import { JwtDTO } from '~/model/JwtDTO'

const authLogin = new AuthLogin()
const secret = '1234'
const usuarios = new Map<string, LoginUsuario>()

let error: any = 'error'
let token: any = 'token'
let session: any = 'session'
const jwtDTO = new JwtDTO()
// Tiempo de duración de un token en segundos
const TOKEN_DURATION = 600

// Tiempo en segundos antes de que se genere un nuevo token
const TOKEN_REFRESH_TIME = 300

// Función para generar un nuevo token y actualizar la sesión del usuario
async function refreshUserToken() {
  jwtDTO.token = session.token.data.token
  token = await authLogin.refreshToken(jwtDTO)
  console.log('TOKEN RECIBIDO!!!!', token)
}

export default eventHandler(async (event) => {
  console.log('****LOGIN!!!!******')
  // Obtenemos los datos del usuario a partir del cuerpo de la petición HTTP
  if (event !== undefined) {
    const { usuario } = await readBody(event)
    session = event.context.session

    if (usuario !== undefined && usuario !== null && !session.user) {
      // Generamos el token JWT con la información del usuario
      const tokenOError = await authLogin.generateToken(usuario)
      if (tokenOError.error) {
        console.log('HA SIDO UN ERRROR EN CREDENCIALES!!!!', tokenOError.error)

        error = tokenOError.error
      }
      else {
        error = ''
        token = tokenOError

        // Agregamos el token al objeto de sesión
        session.token = token
        session.user = usuario
        usuarios.set(usuario.nombreCorto, usuario)
        console.log('El token generado es!!!!!!', session.token)
        // refreshUserToken()
        setInterval(() => {
          refreshUserToken()
        }, TOKEN_DURATION * 1000 - TOKEN_REFRESH_TIME * 1000)
      }

      // Cancelar el intervalo anterior, si existe
      if (event.context.session.intervalId)
        clearInterval(session.intervalId)

      // Guardar el id del intervalo en la sesión
      // event.context.session.intervalId = intervalId;

      // Cancelamos el intervalo cuando ya no necesitamos refrescar el token
      // clearInterval(event.context.session.intervalId);
    }
    // else {
    //   // console.log('Destruyendo la sesión porque no hay usuario.....')
    //   session.destroy
    // }
  }

  // delete event.context.session.intervalId;
  return {
    session,
    token,
    error,
  }
})
