package com.apireserva.repository;

import org.springframework.data.jpa.repository.JpaRepository; //proporciona interfaz predefinidad para operaciones CRUD
import com.apireserva.model.Reserva;  //la carpeta model, la tabla reserva. 

public interface Reservarepository extends JpaRepository<Reserva, Long> {
} //indico que usara Reserva como entidad y Long (como mi llave primaria)

//extiendo la import y heredo los metodos. asi no los defino manualmeente
