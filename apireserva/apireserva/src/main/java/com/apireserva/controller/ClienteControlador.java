package com.apireserva.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;//representa codigos de estado hhtp(200, 2001, 401, etc). 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


// ✅ Importamos el DTO en lugar del modelo
import com.apireserva.dto.ClienteDTO;
import com.apireserva.model.Cliente;
//import com.apireserva.model.Cliente; (ANULADO)
import com.apireserva.service.ClienteService;


@RestController
@RequestMapping("/api/clientes") //cuando alguien haga una http se vaya a buscar a esta ruta base. 
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteService service; // inyectamos el servicio de cliente para que el controlador pueda usar la logica del negocio. 

    //usare lombok import y la @ para evitar el constructor como se hizo en ClienteService
    
    //ahora devuelve una lista de clientes y no desde model, si no desde DTO. 

    @GetMapping
    public List<ClienteDTO> listar() {
        return service.listar();
    }

    //este getMapping es para poder buscar a un cliente por su id.
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Long id) {
    ClienteDTO clienteDTO = service.obtenerPorId(id);
    if (clienteDTO != null) {
        return ResponseEntity.ok(clienteDTO);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    //POST → recibe un DTO en el cuerpo de la petición y lo guarda
    @ResponseStatus(HttpStatus.CREATED) // Devuelve un código de estado 201 (Creado), dato tomado de la guia. 
    @PostMapping
    public ClienteDTO guardar(@RequestBody ClienteDTO clienteDTO) {
        return service.guardar(clienteDTO);
    }

    //no cambia por que solo requiere el ID
    @ResponseStatus(HttpStatus.NO_CONTENT) // Devuelve un código de estado. 
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
