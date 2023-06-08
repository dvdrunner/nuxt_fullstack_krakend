import GenericServiceImpl from './GenericServiceImpl'
import Pedido from '~/model/Pedido'

export default class PedidoService extends GenericServiceImpl<Pedido, number> {
  constructor() {
    super(Pedido, 'http://localhost:3000/api/pedido/')
  }
}
