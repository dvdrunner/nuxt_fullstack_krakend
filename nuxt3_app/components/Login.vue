<script setup lang="ts">
import { LoginUsuario } from '~/model/LoginUsuario'

const state = ref({ count: 0 })
const msg = ref('Hola Paco')
const usuarioLogueado = reactive(new LoginUsuario())
const usuario = ref('')
const contrasena = ref('')
const error = ref('')
let nombreUsuarioLogueado = ref('')

const router = useRouter()

const { data } = await useFetch('/api/login', {
  method: 'POST',
  body: { usuario: undefined },
})

async function iniciarSesion() {
  if (!usuario.value || !contrasena.value) {
    error.value = 'Por favor ingrese el usuario y la contraseña'
  }
  else {
    console.log('Usuario:', usuario.value)
    console.log('Contraseña:', contrasena.value)

    Object.assign(usuarioLogueado, new LoginUsuario(usuario.value, contrasena.value))

    const { data } = await useFetch('/api/login', {
      method: 'POST',
      body: { usuario: usuarioLogueado },
    })
    console.log('*********DATA EN LOGIN.VUE VALE***********>: ', toRaw(data.value))
    // const { session, refresh, update, reset } = await useSession()
    if (data.error) {
      console.log(nombreUsuarioLogueado)
      error.value = 'Credenciales erróneas'
    }
    else if (data?.value?.session?.user?.nombreUsuario) {
      nombreUsuarioLogueado = data.value.session.user.nombreUsuario
      error.value = ''
      console.log(nombreUsuarioLogueado)
    }

    const redirect = router.currentRoute.value.query.redirect?.toString() || '/'
    console.log('Redirect en login es: ', redirect)
    await navigateTo(redirect)
  }
  /** ***PARA PRUEBAS DE SEGURIDAD */
  // const redirect = router.currentRoute.value.query.redirect?.toString() || '/'
  // console.log('Redirect en login es: ', redirect)
  // await navigateTo(redirect)
}
</script>

<template>
  <!-- <GenericComponent :items="[1, 2, 3]" :selected-item="2" />
  <DefineModelComp v-model="state.count" />
  <button @click="msg = 'new message'">
    Nuevo mensaje
  </button>
  <PropsDest :msg="msg" /> -->

  <div class="login" style="display: h; flex-direction: column; align-items: center; justify-content: center;">
    <h2>Iniciar sesión</h2>

    <div class="p-field">
      <label for="usuario">Usuario</label>
      <InputText id="usuario" v-model="usuario" />
    </div>
    <div class="p-field">
      <label for="contrasena">Contraseña</label>
      <Password id="contrasena" v-model="contrasena" />
    </div>
    <div class="p-field">
      <Button type="submit" class="p-button p-button-primary" @click="iniciarSesion">
        Iniciar
        sesión
      </Button>
    </div>

    <p v-if="nombreUsuarioLogueado">
      Usuario logueado: {{ nombreUsuarioLogueado }}
    </p>
    <div v-if="error" class="p-message p-message-error">
      {{ error }}
    </div>
  </div>
</template>

<style lang='scss'>
.login {
  height: 100vh;
  display: flex;
  h2 {
    margin-bottom: 2rem;
  }

  width: 50%;
  display: flex;
  flex-direction: column;

  .p-field {
    margin-bottom: 1rem;
    align-items: center;

    label {
      margin-bottom: 0.5rem;
      display: block;
    }
  }

  .p-button {
    margin-top: 1rem;
  }

  .p-message {
    margin-top: 1rem;
  }
}
</style>
