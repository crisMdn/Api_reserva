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
public class MesaDTO {
    private Long idMesa;

    private Integer num_mesa;

    private Integer capacidad;

    private String ubicacion;

    @Column(name = "estado_mesa")
    private String estadoMesa;
}
