package com.apireserva.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*; //se usa para marcar la clase como entidad en la bs, y JPA se encargara de maquear la clase a una tabla 
import lombok.Data; //se usa para generar get,set y otros metodos. 



//esto es una entidad en la BS

@Data
@Entity //marco la clase como una entidad para la JPA
@NoArgsConstructor //agregaciones, genero constructor con argumentos  
@AllArgsConstructor //constructor sin argumentos

public class Reserva {
    @Id //indica que este campo es la clave primario de esta entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que el valor del identificador se generara automaticamente
    private Long id; 

    @ManyToOne //se agrego; para indicar que una reserva esta asociado a un unico cliente; ademas 
    @JoinColumn(name = "cliente_id") //le decimos a la bs en que columna se guardara la referencia del cliente (el  ID)
    private Cliente cliente; // relación con entidad Cliente; muchos a uno 

    private LocalDateTime fecha; //fechas con formato ISO, igual que el DTE, permite a sql filtrar por fecha/hora en consultas. sin converitr a texto. 

    @Column(length = 25)
    private String servicio; 

}
