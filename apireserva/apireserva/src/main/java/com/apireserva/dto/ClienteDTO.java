package com.apireserva.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data //genera automaticamente todos los getters y setters , constructor vacio si no existe, metodos utiles, toString, equals, hashCode. 
@NoArgsConstructor //creacion automatica de un constructor sin parametros. 
@AllArgsConstructor //constructor con todos los parametros. 
@Builder //implementa patron builden que permite construir objetos de forma mas legible
public class ClienteDTO {
    //anotaciones de validacion. 
    @NotBlank(message = "el nombre es obligatorio") //validacion para nombre
    @Size(max = 10, message = "Nombre muy largo") //validacion con un maximo de 80 caracteres. 
    private String nombre_cliente; //en la base de datos el atributo se llama nombre_cliente

    @Email(message = "formato de correo invalido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    
    @Pattern(regexp ="^[0-9\\-\\+\\s]{8,20}$", message= "Telefono invalido")
    private String telefono;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }
}
