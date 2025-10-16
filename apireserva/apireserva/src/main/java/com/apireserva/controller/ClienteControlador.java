package com.apireserva.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.apireserva.model.Cliente;
import com.apireserva.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    private final ClienteService service;

    public ClienteControlador(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return service.guardar(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
