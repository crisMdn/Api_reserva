package com.apireserva.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.apireserva.model.Cliente;
import com.apireserva.repository.Clienterepository;

@Service
public class ClienteService {

    private final Clienterepository repository;

    public ClienteService(Clienterepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
