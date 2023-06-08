import type { Serializable } from 'node:child_process'
import type { IGenericService } from './IGenericService'
import type { EntidadInfo } from '~/model/EntidadInfo'

export default class GenericServiceImpl<T extends EntidadInfo, ID extends Serializable> implements IGenericService<T, ID> {
  path?: string
  entityList: T[] = []

  constructor(entity?: { new(): T }, path?: string) {
    this.path = path
  }

  async getAll(): Promise<[T[], any]> {
    if (!this.path || typeof this.path !== 'string')
      throw new Error('La ruta no está definida o no es una cadena de texto.')

    const { data: entities, error } = useFetch(this.path)
    const entitiesArray = unref(entities) as T[]
    return [entitiesArray, error]
  }

  async save(entity: T): Promise<T> {
    if (!this.path || typeof this.path !== 'string')
      throw new Error('La ruta no está definida o no es una cadena de texto.')

    const response = await useFetch(this.path, {
      method: 'POST',
      body: JSON.stringify(entity),
    })
    return response.data.value as T
  }

  async remove(id: ID): Promise<T> {
    const response = await useFetch(`${this.path}${id}`, {
      method: 'DELETE',
    })
    return response.data.value as T
  }
}
