package com.apireserva.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Indexed;

import jakarta.persistence.*; //se usa para marcar la clase como entidad en la bs, y JPA se encargara de maquear la clase a una tabla 
import lombok.Data; //se usa para generar get,set y otros metodos. 


//esto es una entidad en la BS

@Data
@Entity //marco la clase como una entidad para la JPA
public class Reserva {
    @id //indica que este campo es la clave primario de esta entidad
    @Generatevalue(strategy = GenerationType.IDENTITY) //indica que el valor del identificador se generara automaticamente
    private Long id; 

    private String cliente; 
    private String fecha;
    private String servicio; 

}
