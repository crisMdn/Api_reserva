package com.apireserva.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;//representa codigos de estado http (200, 201, 401, etc).
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//Importamos el DTO en lugar del modelo
import com.apireserva.dto.ClienteDTO;
import com.apireserva.model.Cliente;
//import com.apireserva.model.Cliente; (ANULADO)
import com.apireserva.service.ClienteService;


@RestController //Nos ayuda a que devuela datos en formato Json
@RequestMapping("/api/clientes") //Ruta base para realizar cualquier consulta HTTP.
@RequiredArgsConstructor //Genera automaticamente todos los constructores
public class ClienteControlador {

    // inyectamos el servicio de cliente para que el controlador pueda usar la logica del negocio.
    private final ClienteService service;
    
    //Devuelve una lista de clientes y no desde model, si no desde DTO.
    @GetMapping
    public List<ClienteDTO> listar() {
        return service.listar();
    }

    //Con este GetMapping podemos buscar a un cliente por su id.
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

    //Se elimima un cliente utilizando su numero de ID
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);

    }

    // PUT → Actualiza un cliente existente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        try {
            // Llama al servicio para actualizar y devuelve 200 OK con el nuevo objeto
            return ResponseEntity.ok(service.actualizar(id, dto));
        } catch (RuntimeException e) {
            // Si no existe el cliente, devuelve 404
            return ResponseEntity.notFound().build();
        }
    }
}
