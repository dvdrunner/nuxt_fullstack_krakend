export interface IGenericService<T, ID> {
  getAll(): Promise<[T[], any]>
  save(entity: T): Promise<T>
  remove(id: ID): Promise<T>
}
