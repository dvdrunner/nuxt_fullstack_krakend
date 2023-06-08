export default defineNuxtRouteMiddleware(async (to, from) => {
  console.log('MIDDLEWARE', to.path, from.path)

  // Si ya estamos en la página de login, no hacemos nada.
  if (to.path.includes('/login')) {
    console.log('Estamos en la página de login')
    return
  }

  // Obtenemos el usuario de la sesión
  const { data } = await useFetch('/api/login', {
    method: 'POST',
    body: { usuario: undefined },
  })

  if (
    data === undefined
    || data === null
    || (data.value == null || !data.value.session.user)
    || (data.value !== null && data.value.session.token === undefined)
  ) {
    console.log('Redirigiendo')
    return navigateTo(`/login?redirect=${to.path}`)
  }
})

/** **SERVER */
// export default eventHandler((event) => {
//     console.log('Hola desde el middleware del server')
//     // Obtenemos el objeto session
//     const session = event.context.session;
//     console.log('La sesión en el middleware server es: ', session)
//     // Si no hay session o no hay user, redirigimos a la página de login
//     if ((!session || !session.user) && event.node.req.url !== undefined && !event.node.req.url.includes('/login')) {
//         console.log('Hola en el middleware server en el if')
//         event.node.res.writeHead(302, { Location: 'http://localhost:3000/login' });
//         event.node.res.end();
//     } else {
//         console.log('Hola en el middleware server en el else')
//     }
// });
