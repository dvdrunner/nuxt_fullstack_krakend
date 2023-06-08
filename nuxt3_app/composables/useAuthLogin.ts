import { LoginUsuario } from "~/model/LoginUsuario";
import { NuevoUsuario } from "~/model/NuevoUsuario";
import { useToast } from "primevue/usetoast";

export function useAuthLogin() {
  const toast = useToast();
  const authURL = "http://localhost:8090/auth/";

  const nuevo = async (nuevoUsuario: NuevoUsuario): Promise<any> => {
    const { data } = await useFetch(authURL + "nuevo", {
      method: "POST",
      body: { nuevoUsuario: nuevoUsuario },
    });

    return data;
  };

  const login = async (loginUsuario: LoginUsuario): Promise<any> => {
    const { data, error } = await useFetch(authURL + "login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(loginUsuario),
    });

    if (error && error.value !== null) {
      console.log("Error:", error.value.data.mensaje);
      toast.add({
        severity: "error",
        summary: "Error",
        detail: "Credenciales incorrectas",
        life: 3000,
      });
      return;
    }
    console.log("El token es:", toRaw(data.value));
    return data;
  };

  return {
    nuevo,
    login,
  };
}
