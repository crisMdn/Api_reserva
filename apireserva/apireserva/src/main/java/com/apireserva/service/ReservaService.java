package com.apireserva.service;

import org.springframework.stereotype.Service; // Indico a Spring que este módulo es de servicio
import java.util.List;
import org.modelmapper.ModelMapper; // facilita la conversion automatica entre entidades y DTO. 
import java.util.stream.Collectors; // Se usa para recopilar los resultados del stream en una lista, permitiendo transformar cada entidad en DTO

import com.apireserva.model.Cliente;
import com.apireserva.model.Reserva;
import com.apireserva.model.Mesa;
import com.apireserva.dto.ReservaDTO;
import com.apireserva.repository.Reservarepository;

import lombok.RequiredArgsConstructor;



@Service // Especifico que este es el servicio
@RequiredArgsConstructor
public class ReservaService {

    private final Reservarepository repository; // Inyección del atributo repository, para que hable con la base de datos. 

    private final ModelMapper modelMapper;  //inyeccion de dependencia Model. 

    //modificacion del metodo referente a los DTO
    //anulado constructor, se agrego @RequeridArgsConstructor

    public List<ReservaDTO> listar() {
        // 1️ Obtiene todos los clientes desde la BD (entidades)
        // 2️ Usa stream() para recorrerlos
        // 3️ Convierte cada Cliente a ClienteDTO
        // 4️ Los recopila en una lista de DTOs
        return repository.findAll() // findAll obtiene todas las reservas
                .stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList()); 
    }

    //modificacion para uso de DTO
public ReservaDTO guardar(ReservaDTO reservaDTO) {
    // 1️⃣ Convertimos el DTO a entidad
    Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);

    // 2️⃣ Creamos un objeto Cliente solo con su id (no se necesita traerlo de la BD)
    Cliente cliente = new Cliente();
    cliente.setId_cliente(reservaDTO.getId_cliente());
    // 3️⃣ Asignamos el cliente a la reserva
    reserva.setCliente(cliente);

    // 3️⃣ Asociamos mesa por ID
    Mesa mesa = new Mesa();
    mesa.setId_mesa(reservaDTO.getId_mesa());
    reserva.setMesa(mesa);
    
     // 4️⃣ Definimos un estado inicial permitido por la BD
    // ⚠️ Opciones válidas: "Confirmada", "Pendiente", "Cancelada", "Completada"
    reserva.setEstado_reserva("Pendiente");

    // 4️⃣ Guardamos la entidad
    Reserva guardada = repository.save(reserva);

    // 5️⃣ Convertimos la entidad guardada a DTO para devolverla
    return modelMapper.map(guardada, ReservaDTO.class);


}

    public void eliminar(Long id) {
        repository.deleteById(id); 
    }
}
