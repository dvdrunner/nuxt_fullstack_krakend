package ea.ciges.krakend.controller;


import ea.ciges.krakend.anotaciones.AfterInsert;
import ea.ciges.krakend.anotaciones.AfterUpdate;
import ea.ciges.krakend.anotaciones.BeforeInsert;
import ea.ciges.krakend.anotaciones.BeforeUpdate;
import ea.ciges.krakend.dto.IDto;
import ea.ciges.krakend.mapper.MapperGeneric;
import ea.ciges.krakend.model.EntidadInfo;
import ea.ciges.krakend.service.BaseServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Slf4j
public abstract class GenericControllerImpl<E extends EntidadInfo, ID extends Serializable, D extends IDto, S extends BaseServiceImpl<E, ID>, M extends MapperGeneric<E, D>>
        implements IGenericController<E, D, ID> {

    @Autowired
    protected S servicio;

    @Autowired
    protected M mapper;

    private List<String> listaErrores = new ArrayList<>();

    private E entity;

    /*
        @GetMapping(value = "/auth-token-info")
        public Map<String, Object> getTokenInfo(Authentication authentication) {
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("auth-user", authenticatedUser);

            return responseBody;
        }
    */
    @GetMapping("/")
    @Transactional
    @Override
    public ResponseEntity<?> getAll() {


        try {
            List<E> entities = servicio.findAll();
            List<D> dtos = mapper.toDtoList(entities);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Mi mensaje get todos\": \"" + e.getMessage() + "\"}");
        }

    }

    /*
        @GetMapping("/")
        @Transactional
        @Override
        public ResponseEntity<?> getAllLazy(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue = "10") Integer pageSize,
                @RequestParam(defaultValue = "id") String sortBy) {
            try {
                Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
                Page<E> pageResult = servicio.findAll(paging);
                List<D> dtos = mapper.toDtoList(pageResult.getContent());
                Map<String, Object> response = new HashMap<>();
                response.put("dtos", dtos);
                response.put("currentPage", pageResult.getNumber());
                response.put("totalItems", pageResult.getTotalElements());
                response.put("totalPages", pageResult.getTotalPages());

                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"Mi mensaje get todos\": \"" + e.getMessage() + "\"}");
            }
        }
    */
    @PostMapping("/")
    @Transactional
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody D dto, BindingResult bindingResult) {
        log.info("Iniciando proceso de guardado de un registro...");

        getListaErrores().clear();
        try {
            entity = mapper.toEntity(dto);
            boolean entityNew = false;
            if (entity.getId() == null) {
                entityNew = true;
                doBeforeInsert();
                log.info("Ejecutando método doBeforeInsert...");
            } else {
                entityNew = false;
                doBeforeUpdate();
                log.info("Ejecutando método doBeforeUpdate...");
            }
            if (bindingResult.hasErrors()) {
                for (FieldError error : bindingResult.getFieldErrors()) {
                    getListaErrores().add(error.getDefaultMessage());
                }
                log.error("Se encontraron errores de binding: {}", getListaErrores());
            }
            if (!getListaErrores().isEmpty()) {
                return new ResponseEntity(getListaErrores(), HttpStatus.BAD_REQUEST);
            }

            log.trace("Objeto a guardar: {}", entity);
            E savedEntity = servicio.save(entity);
            log.trace("Objeto guardado: {}", savedEntity);
            D savedDto = mapper.toDto(savedEntity);

            if (entityNew) {
                doAfterInsert();
                log.info("Ejecutando método doAfterInsert...");
            } else {
                doAfterUpdate();
                log.info("Ejecutando método doAfterUpdate...");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);

        } catch (Exception e) {
            log.error("Error guardando el registro: " + e.getMessage(), e);
            return new ResponseEntity("No se ha podido guardar el registro.", HttpStatus.BAD_REQUEST);
        }
    }


    private void doBeforeInsert() {
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeInsert.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Manejar la excepción
                }
            }
        }
    }

    private void doBeforeUpdate() {
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeUpdate.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Manejar la excepción
                }
            }
        }
    }

    private void doAfterInsert() {
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterInsert.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Manejar la excepción
                }
            }
        }
    }

    private void doAfterUpdate() {
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterUpdate.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // Manejar la excepción
                }
            }
        }
    }

    @PutMapping("/{id}")
    @Transactional
    @Override
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody D dto) {
        try {
            E entity = mapper.toEntity(dto);
            E updatedEntity = servicio.update(id, entity);
            D updatedDto = mapper.toDto(updatedEntity);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Mi mensaje put\": \"" + e.getMessage() + "\"}");
        }
    }


    //@PreAuthorize("hasRole('ROLE_SUPER_USER')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable ID id) {
        try {
            log.info("Eliminando pedido con ID {}", id);
            servicio.delete(id);
            log.info("Pedido eliminado con éxito");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("Error al eliminar pedido con ID {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Mi mensaje delete\": \"" + e.getMessage() + "\"}");
        }
    }


    @GetMapping("/{id}")
    @Transactional
    @Override
    public ResponseEntity<?> getOne(@PathVariable ID id) {
        try {
            E entity = servicio.findById(id);
            if (entity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"No se encontró el registro con id " + id + "\"}");
            }
            D dto = mapper.toDto(entity);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/exists/{id}")
    @Transactional
    @Override
    public ResponseEntity<Boolean> existsById(@PathVariable ID id) {
        try {
            Boolean exists = servicio.existsById(id);
            return ResponseEntity.status(HttpStatus.OK).body(exists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }
    }

    @GetMapping("/count")
    @Transactional
    @Override
    public ResponseEntity<Long> count() {
        try {
            Long count = servicio.count();
            return ResponseEntity.status(HttpStatus.OK).body(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(0L);
        }
    }
}