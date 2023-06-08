<script setup>
let nombreUsuarioLogueado = ref('')
// const { session } = await useSession();
// console.log('La sesión es:',session);
const { data } = await useFetch('/api/login', {
  method: 'POST',
  body: { usuario: undefined },
})
nombreUsuarioLogueado = data?.value?.session?.user?.nombreUsuario
</script>s

<template>
  <p v-if="nombreUsuarioLogueado">
    Usuario logueado: {{ nombreUsuarioLogueado }}
  </p>
</template>

<style lang='scss'></style>
