package com.apireserva.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.apireserva.model.Cliente;
import com.apireserva.service.ClienteService;


@RestController
@RequestMapping("/api/clientes") //cuando alguien haga una http se vaya a buscar a esta ruta
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteService service; // inyectamos el servicio de cliente para que el controlador pueda usar la logica del negocio. 

    //usare lombok import y la @ para evitar el constructor como se hizo en ClienteService
    

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @ResponseStatus(HttpStatus.CREATED) // Devuelve un código de estado 201 (Creado), dato tomado de la guia. 
    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return service.guardar(cliente);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // Devuelve un código de estado. 
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
