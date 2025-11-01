// src/main/java/com/apireserva/dto/ReservaDTO.java
package com.apireserva.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime; //libreria para validor de fecha/tiempo. 


@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class ReservaDTO {

    @NotNull(message = "La fecha/hora es obligatoria")
    private LocalDateTime fecha_reservada;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "Mínimo 1 persona") //validador de cantidad minimo 
    @Max(value = 20, message = "Máximo 20 personas") //validador de cantidad maxima //obcional. 
    private Integer personas;


    @NotNull(message = "Debe indicar el ID del cliente") //validador de obligacion en campo. 
    private Long id_cliente; // relación con Cliente

    @NotNull(message= "agregar una mesa")
    private Long id_mesa;

    @NotBlank(message = "Debe indicar el turno de la reserva")
    private String turno;

    private String estado_reserva;



}
