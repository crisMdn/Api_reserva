package com.apireserva.repository;

import org.springframework.data.jpa.repository.JpaRepository; //proporciona interfaz predefinidad para operaciones CRUD
import com.apireserva.model.Reserva;  //la carpeta model, la tabla reserva.
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

// Esta parte permite que se puedan realizar todas las consultas HTTP o CRUD en Reserva
public interface Reservarepository extends JpaRepository<Reserva, Long> {

}
//indico que usara Reserva como entidad y Long (como mi llave primaria)

//extiendo la import y heredo los metodos. asi no los defino manualmeente
