package com.apireserva.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mesa;

    private Integer num_mesa;

    private Integer capacidad;

    @Column(length = 80)
    private String ubicacion;

    @Column(length = 20)
    private String estado_mesa;

    private LocalDateTime created_at;
}
