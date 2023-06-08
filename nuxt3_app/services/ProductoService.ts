import GenericServiceImpl from './GenericServiceImpl'
import Producto from '~/model/Producto'

export default class ProductoService extends GenericServiceImpl<Producto, number> {
  constructor() {
    super(Producto, 'http://localhost:3000/api/producto/')
  }
}
