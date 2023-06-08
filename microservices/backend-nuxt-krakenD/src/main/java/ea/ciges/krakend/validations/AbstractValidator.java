package ea.ciges.krakend.validations;

import ea.ciges.krakend.model.EntidadID;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public abstract class AbstractValidator<E extends EntidadID<?>, V extends AbstractValidator<? , ?>> {

    private E e;

    public static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public V valida(E e) {
        this.e = e;
        errores.clear();
        return (V) this;
    }

    protected E getEntidad() {
        return e;
    }

    protected List<String> getErrores() {
        return errores;
    }

    private List<String> errores = new ArrayList<>();

    public boolean isValido() {
        return this.errores.isEmpty();
    }

    public List<String> metodos(){
        List<String> rm = new ArrayList<>();
        if(errores.size() > 1){
            rm.add(errores.get(0));
        } else {
            rm.addAll(errores);
        }
        for(int i = 1; i < errores.size(); i++){
            rm.add(errores.get(i));
        }
        return rm;
    }

    public List<String> anotaciones(){
        List<String> ra = new ArrayList<>();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<E>> violations = validator.validate(e);
        for (ConstraintViolation<E> cv : violations) {
            ra.add(cv.getMessage());
        }
        return ra;
    }

    public List<String> todo(){
        anotaciones();
        return metodos();
    }

}


