package ea.ciges.krakend.controller;


import ea.ciges.krakend.dto.IDto;
import ea.ciges.krakend.model.EntidadInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface IGenericController<E extends EntidadInfo, D extends IDto, ID extends Serializable> {
    ResponseEntity<?> getAll();

    ResponseEntity<?> save(D dto, BindingResult bindingResult);


    ResponseEntity<Boolean> existsById(ID id);

    ResponseEntity<Long> count();

    ResponseEntity<?> update(@PathVariable ID id, @RequestBody D dto);

    ResponseEntity<?> delete(@PathVariable ID id);

    ResponseEntity<?> getOne(@PathVariable ID id);
}
