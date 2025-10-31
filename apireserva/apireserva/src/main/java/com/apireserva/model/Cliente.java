package com.apireserva.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*; //se usa para marcar la clase como entidad en la bs, y JPA se encargara de maquear la clase a una tabla 
import lombok.Data; //se usa para generar get,set y otros metodos. 

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "Clientes"), esto nos ayudara a indicar a que referencia hace la tabla en la bs 
public class Cliente {

    @Id //anotaciones de mapeo JPA. INdica como se relaciona con la base de datos. 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente") //se agrego para poder buscar a un cliente con su id
    private Long id;

    //NOTA: LAS VALIDACIONES FUERON PASADAS A LOS DTO. 

    //INTEGRACION DE VALIDACIONES
    //@NotBlank(message = "El nombre no puede estar vacío")
    //@Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre_cliente;

    //@NotBlank(message = "El correo no puede estar vacío")
    //@Email(message = "El correo debe tener un formato válido")
    private String correo;

    //evita que las consultas generen un bucle infinito
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Reserva> reservas; 

    private String telefono;

}