import Estado from "~/model/Estado";
import Pedido from "~/model/Pedido";
import PedidoService from "~/services/PedidoService";

export function usePedidosBean() {

  const metodosDePago: string[] = [
    'Paypal',
    'Bizum',
    'Tarjeta Visa'
  ];



  const { data: estados, error: error } = useFetch<Estado[]>('http://localhost:3000/api/estado/');
  // console.log(toRaw(estados.value));



  const { getAll,
    create,
    save,
    remove,
    rowSelectListener,
    state } = useTableBean<Pedido, number, PedidoService>(Pedido, new PedidoService(),'http://localhost:3000/api/pedido/');


    onMounted(() => {
      getAll().then(() => {
 

        // Verificar si state.list no es null antes de recorrerlo
        if (state.list) {
          // Recorremos el array y transformamos los objetos
          (state.list as Pedido[]).forEach((d: Pedido) => {
            if (d.fechaPedido) {
              d.fechaPedido = new Date(d.fechaPedido);
            }
          });
        }
      });
    })
  







  const beforeCreate = () => {
    create();
  };

  const beforeSave = async () => {
    console.log("beforeSave");
    await save(state.editedRow);
    state.disabled = true;
  };
  const beforeDelete = async () => {
    console.log("beforeSave");
    if (state.editedRow.id !== undefined) {
      await remove(state.editedRow.id);
    }
    state.disabled = true;
  };




  return {
    estados,
    error,
    beforeCreate,
    beforeSave,
    beforeDelete,
    metodosDePago,
    state,
    getAll,
    save,
    remove,
    rowSelectListener,
  };
}
