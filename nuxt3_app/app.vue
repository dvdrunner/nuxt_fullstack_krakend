<template #nav>
  <div>
    <NuxtLayout>
      <Header v-if="$route.path !== '/login'" />

      <nav v-if="$route.path !== '/login'" class="flex align-center gap-4 p-4">
        <NuxtLink to="/" class="n-link-base">
          Home
        </NuxtLink>
        <NuxtLink to="/pedidoscrud/pedidos" class="n-link-base">
          Pedidos
        </NuxtLink>
      </nav>

      <NuxtPage />
    </NuxtLayout>
  </div>
</template>
