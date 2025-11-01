// src/main/java/com/apireserva/dto/ReservaDTO.java
package com.apireserva.dto;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime; //libreria para validar de fecha/tiempo. 

import com.fasterxml.jackson.annotation.JsonFormat;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {

    @NotNull(message = "La fecha/hora es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "fecha_reservada")
    private LocalDateTime fecha_reservada;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "Mínimo 1 persona")
    @Max(value = 20, message = "Máximo 20 personas")
    private Integer personas;

    @NotNull(message = "El estado de la reserva es obligatorio")
    @Size(max = 25, message = "Máximo 25 caracteres")
    private String estado_reserva;

    @NotNull(message = "Debe indicar el turno")
    @Size(max = 20, message = "Máximo 20 caracteres")
    private String turno;

    @NotNull(message = "Debe indicar el ID de la mesa reservada")
    private Long id_mesa;

    @NotNull(message = "Debe indicar el ID del cliente")
    private Long id_cliente;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}
