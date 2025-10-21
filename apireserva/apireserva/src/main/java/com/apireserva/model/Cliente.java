package com.apireserva.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*; //se usa para marcar la clase como entidad en la bs, y JPA se encargara de maquear la clase a una tabla 
import lombok.Data; //se usa para generar get,set y otros metodos. 
import jakarta.validation.constraints.*; //IMPORTACIONES PARA VALIDACIONES. 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //INTEGRACION DE VALIDACIONES
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;
}