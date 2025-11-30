package com.apireserva.repository;

import org.springframework.data.jpa.repository.JpaRepository; //Es una interfaz de Spring Data JPA que provee métodos listos para usar en operaciones CRUD
import com.apireserva.model.Cliente; //De la carpeta model, la tabla Cliente. 

// Esta parte permite que se puedan realizar todas las consultas HTTP o CRUD en Cliente
public interface Clienterepository extends JpaRepository<Cliente, Long> {
}






