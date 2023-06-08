import GenericServiceImpl from './GenericServiceImpl'
import Estado from '~/model/Estado'

export default class EstadoService extends GenericServiceImpl<Estado, number> {
  constructor() {
    super(Estado, 'http://localhost:3000/api/estado/')
  }
}
