import { defineStore } from 'pinia';
import { Usuario } from "~/model/Usuario";

export const useUserLogueado = defineStore({
  id: 'userLogueado',
  state: () => ({
    usuarioLogueado: new Usuario,
  }),
  actions: {
    setUsuarioLogueado(usuario) {
      this.usuarioLogueado = new Usuario(usuario);
    },
  },
});