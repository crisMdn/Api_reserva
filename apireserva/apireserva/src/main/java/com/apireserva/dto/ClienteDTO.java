package com.apireserva.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Data //genera automaticamente todos los getters y setters , constructor vacio si no existe, metodos utiles, toString, equals, hashCode. 
@NoArgsConstructor //creacion automatica de un constructor sin parametros. 
@AllArgsConstructor //constructor con todos los parametros. 
@Builder //implementa patron builden que permite construir objetos de forma mas legible
public class ClienteDTO {
    //anotaciones de validacion. 
    @NotBlank(message = "el nombre es obligatorio") //validacion para nombre
    @Size(max = 10, message = "Nombre muy largo") //validacion con un maximo de 80 caracteres. 
    private String nombre_cliente; 

    @Email(message = "formato de correo invalido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    
    
    @Pattern(regexp ="^[0-9\\-\\+\\s]{8, 20}$", message= "Telefono invalido")
    private String telefono; 
}
