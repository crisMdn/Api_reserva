package com.apireserva.service;

import com.apireserva.model.Cliente;
import org.springframework.stereotype.Service; // Indico a Spring que este módulo es de servicio
import java.util.List;
import org.modelmapper.ModelMapper; // facilita la conversion automatica entre entidades y DTO. 
import java.util.stream.Collectors; // Se usa para recopilar los resultados del stream en una lista, permitiendo transformar cada entidad en DTO

import com.apireserva.model.Reserva;
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
                .map(reserva -> { ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);
                            dto.setId_cliente(reserva.getCliente().getId_cliente()); // ← extrae el ID manualmente
                            return dto;
                })
                            .collect(Collectors.toList());
    }

    //obtener una reserva por su numero de ID.
    public ReservaDTO obtenerPorId(Long id) {
    return repository.findById(id)
        .map(reserva -> {
            ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);
            dto.setId_cliente(reserva.getCliente().getId_cliente());
            return dto;
        })
        .orElse(null);
}

    //modificacion para uso de DTO
    public ReservaDTO guardar(ReservaDTO reservaDTO) {
         //Convierte el DTO a entidad para guardarla
         Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);

        // Crear objeto Cliente con solo el ID
        Cliente cliente = new Cliente();
        cliente.setId_cliente(reservaDTO.getId_cliente());
        reserva.setCliente(cliente);

         //guarda la entidad
         Reserva guardada = repository.save(reserva);

        // 🔄 Convertir entidad guardada a DTO
        ReservaDTO dto = modelMapper.map(guardada, ReservaDTO.class);
        dto.setId_cliente(guardada.getCliente().getId_cliente());
        return dto;
         //convierte la entidad guardada a DTO para devolverla. 
        //return modelMapper.map(guardada, ReservaDTO.class);
    }

    public void eliminar(Long id) {
        repository.deleteById(id); 
    }
}
