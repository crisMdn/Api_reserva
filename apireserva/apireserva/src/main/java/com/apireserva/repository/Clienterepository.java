package com.apireserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apireserva.model.Cliente;

public interface Clienterepository extends JpaRepository<Cliente, Long> {
}
