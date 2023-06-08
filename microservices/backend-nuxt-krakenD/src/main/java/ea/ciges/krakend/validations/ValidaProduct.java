package ea.ciges.krakend.validations;


import ea.ciges.krakend.model.Producto;
import ea.ciges.krakend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidaProduct extends AbstractValidator<Producto, ValidaProduct> {

    @Autowired
    private ProductRepository productRepository;

    public ValidaProduct validarNombreRepetido() {
// usar el repositorio para realizar alguna validación
        Producto producto = productRepository.findByNombreAndValido(getEntidad().getNombre(), true).orElse(null);
        if (producto != null) {
            getErrores().add("El producto con ese nombre ya existe.");
        }
        return this;
    }

}
