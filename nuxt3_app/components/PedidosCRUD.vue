<script setup lang="ts">
import { FilterMatchMode, FilterOperator } from 'primevue/api'
import { Usuario } from '~/model/Usuario'

// composable para utils con la lógica del componente
const {
  beforeCreate, beforeSave, beforeDelete, metodosDePago, state, estados,
  error, rowSelectListener,
} = usePedidosBean()

const filters = ref({
  global: { value: null, matchMode: FilterMatchMode.CONTAINS },
  id: { value: null, matchMode: FilterMatchMode.CONTAINS },
  estado: { value: null, matchMode: FilterMatchMode.CONTAINS },
  precioTotal: { value: null, matchMode: FilterMatchMode.CONTAINS },
  direccionEnvio: { value: null, matchMode: FilterMatchMode.CONTAINS },
  metodoDePago: { value: null, matchMode: FilterMatchMode.CONTAINS },
  numSeguimiento: { value: null, matchMode: FilterMatchMode.CONTAINS },
  observaciones: { value: null, matchMode: FilterMatchMode.CONTAINS },
  fechaPedido: { operator: FilterOperator.AND, constraints: [{ value: null, matchMode: FilterMatchMode.DATE_IS }] },
  esPedidoCaro: { value: null, matchMode: FilterMatchMode.EQUALS },
})

function formatDate(fechaPedido: string) {
  const date = new Date(fechaPedido)
  return date.toLocaleDateString('en-ES', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
  })
}

async function navigateToHome() {
  await navigateTo('/')
}
const { data: user } = useFetch('/api/usuarios', {
  method: 'POST',
  body: JSON.stringify(new Usuario()),
})
</script>

<template>
  <p v-if="user">
    Usuario logueado: {{ user._nombreCorto }}
  </p>

  <Button @click="navigateToHome" />
  <Toast />
  <p>
    {{ state.editedRow.numSeguimiento === undefined ? 'No hay número de seguimiento' : state.editedRow?.numSeguimiento }}
  </p>

  <div class="mx-auto w-4/5">
    <Panel header="Pedidos disponibles">
      <DataTable
        v-model:selection="state.selectedRow" :value="state.list" :paginator="true" :rows="10"
        class="p-datatable-productos" selection-mode="single" data-key="id" v-model:filters="filters"
        paginator-template="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
        :rows-per-page-options="[10, 20, 50]" responsive="true" filter-display="menu" :global-filter-fields="['id', 'estado.nombre', 'precioTotal', 'direccionEnvio', 'metodoDePago', 'numSeguimiento', 'observaciones', 'fechaPedido', 'esPedidoCaro']"
        current-page-report-template="Mostrando {first} a {last} de {totalRecords}"
        @rowSelect="rowSelectListener"
      >
        <template #header>
          <div class="flex justify-content-end">
            <span class="p-input-icon-left ">
              <i class="pi pi-search" />
              <InputText v-model="filters.global.value" placeholder="Buscar..." />
            </span>
          </div>
        </template>
        <template #empty>
          No hay pedidos disponibles.
        </template>
        <template #loading>
          Cargando pedidos. por favor, espera.
        </template>
        <Column ref="id" field="id" header="Id" filter-match-mode="contains" :sortable="true">
          <template #filter="{ filterModel, filterCallback }">
            <InputText
              v-model="filterModel.value" class="p-column-filter" placeholder="Buscar por id "
              @keyup="filterCallback()"
            />
          </template>
        </Column>

        <Column header="Estado" sortable sort-field="estado.nombre" filter-field="estado.nombre" style="min-width: 14rem">
          <template #body="{ data }">
            <div class="flex align-items-center gap-2">
              <Tag :value="data.estado ? data.estado.nombre : '-'" />
            </div>
          </template>
          <template #filter="{ filterModel }">
            <InputText v-model="filterModel.value" type="text" class="p-column-filter" placeholder="Search by state" />
          </template>
        </Column>

        <Column ref="precioTotal" field="precioTotal" header="Precio Total" filter-match-mode="contains" :sortable="true">
          <template #filter="{ filterModel, filterCallback }">
            <InputNumber
              v-model="filterModel.value" class="p-column-filter" placeholder="Buscar por precio total"
              @keyup="filterCallback()"
            />
          </template>
        </Column>
        <Column
          ref="direccionEnvio" field="direccionEnvio" header="Dirección de Envío" filter-match-mode="contains"
          :sortable="true"
        >
          <template #filter="{ filterModel, filterCallback }">
            <InputText
              v-model="filterModel.value" class="p-column-filter" placeholder="Buscar por dirección de envío"
              @keyup="filterCallback()"
            />
          </template>
        </Column>
        <Column
          ref="metodoDePago" field="metodoDePago" header="Método de Pago" filter-match-mode="contains"
          :sortable="true"
        >
          <template #filter="{ filterModel, filterCallback }">
            <InputText
              v-model="filterModel.value" class="p-column-filter" placeholder="Buscar por método de pago"
              @keyup="filterCallback()"
            />
          </template>
        </Column>
        <Column
          ref="numSeguimiento" field="numSeguimiento" header="Número de Seguimiento" filter-match-mode="contains"
          :sortable="true"
        >
          <template #filter="{ filterModel, filterCallback }">
            <InputText
              v-model="filterModel.value" class="p-column-filter" placeholder="Buscar por número de seguimiento"
              @keyup="filterCallback()"
            />
          </template>
        </Column>
        <Column
          ref="observaciones" field="observaciones" header="Observaciones" filter-match-mode="contains"
          :sortable="true"
        >
          <template #filter="{ filterModel, filterCallback }">
            <FormKit
              v-model="filterModel.value" type="text" class="p-column-filter" placeholder="Buscar por observaciones"
              @keyup="filterCallback()"
            />
          </template>
        </Column>

        <Column header="Fecha del pedido" filter-field="fechaPedido" data-type="date" style="min-width: 10rem">
          <template #body="{ data }">
            {{ formatDate(data.fechaPedido) }}
          </template>
          <template #filter="{ filterModel }">
            <Calendar v-model="filterModel.value" date-format="dd/mm/yy" placeholder="dd/mm/yyyy" mask="99/99/9999" />
          </template>
        </Column>

        <Column
          field="esPedidoCaro" header="esPedidoCaro" data-type="boolean" body-class="text-center"
          style="min-width: 8rem"
        >
          <template #body="{ data }">
            <i
              class="pi"
              :class="{ 'pi-check-circle text-green-500 ': data.esPedidoCaro, 'pi-times-circle text-red-500': !data.esPedidoCaro }"
            />
          </template>
          <template #filter="{ filterModel }">
            <label for="verified-filter" class="font-bold"> Verified </label>
            <TriStateCheckbox v-model="filterModel.value" input-id="verified-filter" />
          </template>
        </Column>

        <!-- <Column field="esPedidoCaro" header="esPedidoCaro" dataType="boolean" style="min-width: 6rem">
                <template #body="{ data }">
                  <i class="pi"
                    :class="{ 'pi-check-circle text-green-500': data.esPedidoCaro, 'pi-times-circle text-red-400': !data.esPedidoCaro }"></i>
                </template>
                <template #filter="{ filterModel, filterCallback }">
                  <TriStateCheckbox v-model="filterModel.value" @change="filterCallback()" />
                </template>
              </Column> -->
      </DataTable>
    </Panel>
  </div>

  <Panel style="margin-bottom: 20px;margin: 0 auto; width: 80%">
    <div class="panel-flex" style="margin-bottom: 20px;margin: 0 auto; width: 80%">
      <div style="display: inline-block; margin-right: 10px; vertical-align: middle;" outer-class="mb-5">
        <label for="numSeguimiento" style="display: block;">Nº de seguimiento</label>
        <FormKit id="numSeguimiento" v-model="state.editedRow.numSeguimiento" rules="required" type="text" />
      </div>
      <div style="display: inline-block; margin-right: 10px; vertical-align: middle;">
        <label for="metodoDePago" style="display: block;">Método de pago</label>
        <FormKit
          id="metodoDePago" v-model="state.editedRow.metodoDePago" type="select" :options="metodosDePago"
          placeholder="Selecciona un método de pago"
        />
      </div>

      <div>
        <h1 v-if="error">
          Error al cargar el componente
        </h1>
        <div v-else>
          <div style="display: inline-block; margin-right: 10px; vertical-align: middle;">
            <label for="estado" style="display: block;">Estado</label>
            <Dropdown
              id="estado" v-model="state.editedRow.estado" :disabled="state.disabled" style="width: 200px;"
              option-label="nombre" placeholder="Selecciona un estado" :options="estados ?? []"
            />
          </div>
        </div>
      </div>

      <div style="display: inline-block; margin-right: 10px; vertical-align: middle;">
        <label for="direccionEnvio" style="display: block;">Dirección de envío</label>
        <InputText
          id="direccionEnvio" v-model="state.editedRow.direccionEnvio" type="text" :disabled="state.disabled"
          style="width: 200px;"
        />
      </div>
      <div style="display: inline-block; margin-right: 10px; vertical-align: middle;">
        <label for="precioTotal" style="display: block;">Precio total</label>
        <InputNumber
          id="precioTotal" v-model="state.editedRow.precioTotal" type="number" :disabled="state.disabled"
          :min-fraction-digits="2" style="width: 200px;"
        />
      </div>

      <div style="display: inline-block; margin-right: 10px; vertical-align: top;">
        <label for="observaciones" style="display: block;">Observaciones</label>
        <Textarea
          id="observaciones" v-model="state.editedRow.observaciones" :auto-resize="true" rows="5" cols="90"
          type="text" :disabled="state.disabled" style="width: 350px;"
        />
      </div>
    </div>
  </Panel>

  <div>
    <Panel style="margin-bottom: 20px;margin: 0 auto; width: 80%">
      <div class="center-buttons" style="margin: 0 auto; width: 80%; text-align: center;">
        <Button
          style="margin-right: 10px;" label="Nuevo" class="p-button-success mr-2" icon="pi pi-plus"
          @click="beforeCreate"
        />
        <Button style="margin-right: 10px;" label="Guardar" icon="pi pi-save" update="nombre" @click="beforeSave" />
        <Button
          style="margin-right: 10px;" label="Eliminar" class="p-button-danger" icon="pi pi-trash"
          @click="beforeDelete"
        />
      </div>
    </Panel>
  </div>
</template>

<style lang="scss" scoped>
.panel-flex {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-start;
}

.panel-flex>div {
  flex-basis: calc(25% - 10px);
  margin-right: 10px;
  margin-bottom: 20px;
}

::v-deep(.p-datatable.p-datatable-productos) {
  .p-datatable-header {
    padding: 1rem;
    text-align: left;
    font-size: 1.5rem;
  }

  .p-paginator {
    padding: 1rem;
  }

  .p-datatable-thead>tr>th {
    text-align: left;
  }

  .p-datatable-tbody>tr>td {
    cursor: auto;
  }

  .p-dropdown-label:not(.p-placeholder) {
    text-transform: uppercase;
  }
}
</style>
