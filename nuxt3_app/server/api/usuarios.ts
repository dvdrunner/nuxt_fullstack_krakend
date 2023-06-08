import jwt from "jsonwebtoken";
import * as crypto from "crypto";
import { Usuario } from "~/model/Usuario";
import dotenv from "dotenv";
dotenv.config({ path: "secret.env" });
// Generamos una clave secreta aleatoria de 32 bytes
// const secret = process.env.SECRET ?? "default_secret";
const secret = "1234";
// Definimos un mapa para almacenar los usuarios
const usuarios = new Map<string, Usuario>();

// Tiempo de duración de un token en segundos
const TOKEN_DURATION = 600;

// Tiempo en segundos antes de que se genere un nuevo token
const TOKEN_REFRESH_TIME = 300;

// Función para generar un nuevo token y actualizar la sesión del usuario
function refreshUserToken(session: any) {
  const usuario = session.user;

  // Generamos el nuevo token JWT con la información del usuario
  const token = jwt.sign(
    { id: usuario.id, nombreCorto: usuario.nombreCorto },
    secret,
    { expiresIn: `${TOKEN_DURATION}s` }
  );

  // Actualizamos el token en la sesión del usuario
  session.token = token;
  console.log("Nuevo token después de actulizarse!!!!!!!", session.token);
}

export default eventHandler(async (event) => {
  console.log("eL SECRET ESSSSSS", secret);
  // Obtenemos los datos del usuario a partir del cuerpo de la petición HTTP
  if (event !== undefined) {
    const { usuario } = await readBody(event);
    let session = event.context.session;

    if (usuario !== undefined && usuario !== null && !session.user) {
      // Agregamos el usuario al mapa
      usuarios.set(usuario.nombreCorto, usuario);
      event.context.session.user = usuario;

      // Generamos el token JWT con la información del usuario
      const token = jwt.sign(
        { id: usuario.id, nombreCorto: usuario.nombreCorto },
        secret,
        { expiresIn: `${TOKEN_DURATION}s` }
      );

      // Agregamos el token al objeto de sesión
      event.context.session.token = token;
      console.log("El token en usuarios.ts es: ", token);

      // Cancelar el intervalo anterior, si existe
      if (event.context.session.intervalId) {
        clearInterval(event.context.session.intervalId);
      }

      // Establecer un nuevo intervalo
      const intervalId = setInterval(() => {
        refreshUserToken(event.context.session);
      }, TOKEN_DURATION * 1000 - TOKEN_REFRESH_TIME * 1000);

      // Guardar el id del intervalo en la sesión
      // event.context.session.intervalId = intervalId;

      // Cancelamos el intervalo cuando ya no necesitamos refrescar el token
      //clearInterval(event.context.session.intervalId);
    } else {
      // console.log('Destruyendo la sesión porque no hay usuario.....')
      event.context.session.destroy;
    }
  }
  console.log(
    "Antes de enviar la sesión desde usuarios.ts vale: ",
    event.context.session
  );
  // delete event.context.session.intervalId;
  return event.context.session;
});
// import { Usuario } from "~/model/Usuario";
// // Definimos un mapa para almacenar los usuarios
// const usuarios = new Map<string, Usuario>();
// export default eventHandler(async (event) => {

//   // Obtenemos los datos del usuario a partir del cuerpo de la petición HTTP
//   if (event !== undefined) {
//     const { usuario } = await readBody(event);
//     let session = event.context.session;
//     console.log('El usuario de la sesión es: ', session.user)
//     if (usuario !== undefined && usuario !== null && !session.user) {
//       console.log('ENTRANDO EN EL IF DE USUARIOS.TS!!!!!!!!!!!!!!!*****************')
//       // Agregamos el usuario al mapa
//       usuarios.set(usuario.nombreCorto, usuario);
//       event.context.session.user = usuario;
//       // session.set('user', usuarioLogueado)();
//       // Devolvemos el usuario en la respuesta
//       console.log('Usuario que llega a usuarios.ts es: ', event.context.session.user);
//     } else {
//       // console.log('Destruyendo la sesión porque no hay usuario.....')
//       event.context.session.destroy;
//     }
//   }
//   console.log('Antes de enviar la sesión desde usuarios.ts vale: ', event.context.session);

//   return event.context.session;

// });
