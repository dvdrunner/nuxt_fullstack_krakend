// TClass: new () => T significa que se espera una clase (TClass) como argumento
// y que se espera que devuelva una instancia de esa clase (new () => T).

import type { Serializable } from 'node:child_process'
import { useToast } from 'primevue/usetoast'
import type { EntidadInfo } from '~/model/EntidadInfo'
import type GenericServiceImpl from '~/services/GenericServiceImpl'

export function useTableBean<T extends EntidadInfo, ID extends Serializable, S extends GenericServiceImpl<T, ID>>(EntityClass: { new(): T }, serviceInstance: S, path: string) {
  const service: S = serviceInstance
  const toast = useToast()
  const showErrorToast = (error: any) => {
    if (error.response.status === 400) {
      const errorMessage = error.response.data
      toast.add({
        severity: 'error',
        summary: 'Error',
        detail: errorMessage,
        life: 3000,
      })
    }
    else {
      console.log('segundo catch')
      console.log(error)
      toast.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Ocurrió un error al guardar el registro. Por favor, inténtalo de nuevo más tarde.',
        life: 3000,
      })
    }
  }
  const state = reactive({
    editedRow: new EntityClass(),
    selectedRow: new EntityClass(),
    list: [] as any,
    disabled: true,
  })

  const getAll = async () => {
    const { data, error } = useFetch(path)
    if (data != null) {
      state.list = data
      console.log('data es: ', data)
    }
    if (error && error.value !== undefined && error.value !== null) {
      console.log('Error:', toRaw(error.value.message))
      console.log('Error:', toRaw(error.value.statusCode))
    }
  }

  const create = () => {
    state.disabled = false
    Object.assign(state.editedRow, new EntityClass())
  }

  const save = async (entity: T): Promise<T> => {
    console.log('save del tableBean')
    try {
      const savedEntity = await service.save(entity)
      await nextTick()
      state.list = await service.getAll()
      const isSuccess = savedEntity !== null
      const toastSeverity = isSuccess ? 'success' : 'error'
      const toastDetail = isSuccess ? 'REGISTRO CREADO CORRECTAMENTE. GUARDADO EN BASE DE DATOS' : 'Ocurrió un error al guardar el registro. Por favor, inténtalo de nuevo más tarde.'
      toast.add({
        severity: toastSeverity,
        summary: 'Guardando',
        detail: toastDetail,
        life: 3000,
      })
      state.disabled = true
      return savedEntity
    }
    catch (error) {
      console.log('primer catch')
      console.log(error)
      showErrorToast(error)
      throw error
    }
  }

  const remove = async (id: ID): Promise<void> => {
    try {
      const deletedEntity = await service.remove(id)
      await nextTick()
      state.list = await service.getAll()
      const isSuccess = deletedEntity !== null
      const toastSeverity = isSuccess ? 'success' : 'error'
      const toastDetail = isSuccess ? 'REGISTRO ELIMINADO CORRECTAMENTE. ELIMINADO DE BASE DE DATOS' : 'Ocurrió un error al eliminar el registro. Por favor, inténtalo de nuevo más tarde.'
      toast.add({
        severity: toastSeverity,
        summary: 'Eliminando',
        detail: toastDetail,
        life: 3000,
      })
    }
    catch (error) {
      console.log('primer catch')
      console.log(error)
      showErrorToast(error)
      throw error
    }
  }

  const rowSelectListener = (selectedRow: T): void => {
    console.log('rowSelectListener del useTableBean')
    console.log('selectedRow:  --->', selectedRow)

    state.editedRow = Object.assign({}, toRaw(state.selectedRow))
    // obtenemos el objeto subyacente
    state.disabled = false

    console.log('numSeguimiento en useTableBean', state.editedRow)
  }

  return {
    getAll,
    save,
    remove,
    rowSelectListener,
    state,
    create,

  }
}
