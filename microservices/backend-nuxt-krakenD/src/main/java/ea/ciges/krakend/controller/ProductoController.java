package ea.ciges.krakend.controller;



import ea.ciges.krakend.dto.ProductoDto;
import ea.ciges.krakend.mapper.ProductoMapper;
import ea.ciges.krakend.model.Producto;

import ea.ciges.krakend.service.ProductServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@Setter
@Getter
public class ProductoController extends GenericControllerImpl<Producto, Long, ProductoDto, ProductServiceImpl, ProductoMapper> {
}
