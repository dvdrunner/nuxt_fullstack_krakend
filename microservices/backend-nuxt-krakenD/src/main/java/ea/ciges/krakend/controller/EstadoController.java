package ea.ciges.krakend.controller;



import ea.ciges.krakend.dto.EstadoDto;
import ea.ciges.krakend.mapper.EstadoMapper;
import ea.ciges.krakend.model.Estado;
import ea.ciges.krakend.service.EstadoServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estado")
//@RolesAllowed({"product_read"})
//Abarca la clase en general, despues cada metodo puede especificar que rol tiene permiso
@Setter
@Getter
public class EstadoController extends GenericControllerImpl<Estado, Long, EstadoDto, EstadoServiceImpl, EstadoMapper> {
}
