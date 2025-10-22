package com.apireserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apireserva.model.Cliente; //De la carpeta model, la tabla Cliente. 

public interface Clienterepository extends JpaRepository<Cliente, Long> {
}






