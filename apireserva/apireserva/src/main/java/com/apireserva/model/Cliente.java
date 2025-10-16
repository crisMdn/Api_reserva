package com.apireserva.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*; //se usa para marcar la clase como entidad en la bs, y JPA se encargara de maquear la clase a una tabla 
import lombok.Data; //se usa para generar get,set y otros metodos. 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
}