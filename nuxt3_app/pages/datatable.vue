<script setup>
const crudColumns = [
  { title: 'ID', field: 'id' },
  { title: 'Nº de seguimiento', field: 'numSeguimiento' },
  { title: 'Método de pago', field: 'metodoDePago' },
  { title: 'Estado', field: 'estado?.nombre' },
  { title: 'Precio total', field: 'precioTotal' },
  { title: 'Dirección de envío', field: 'direccionEnvio' },
  { title: 'Observaciones', field: 'observaciones' },
  {
    title: 'Fecha del pedido', field: 'fechaPedido'


  },
];
const header = "Lista de Pedidos";

const state = reactive({
  editedRow: {},
  selectedRow: {},
  list: [],
  disabled: false,
});

</script>
<template>
  <GenericDatatable :columns="crudColumns" :header="header" :state="tableBean">
  </GenericDatatable>
</template>