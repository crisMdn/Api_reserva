package com.apireserva.service;


import com.apireserva.dto.MesaDTO;
import com.apireserva.model.Cliente;
import com.apireserva.model.Mesa;
import com.apireserva.repository.Clienterepository;
import com.apireserva.repository.Mesarepository;
import org.springframework.stereotype.Service; // Indico a Spring que este módulo es de servicio

import java.time.LocalDateTime;
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
    private final Clienterepository clienteRepository;
    private final Mesarepository  mesaRepository;
    private final ModelMapper modelMapper;  //inyeccion de dependencia Model.

    //modificacion del metodo referente a los DTO
    //anulado constructor, se agrego @RequeridArgsConstructor
    public List<ReservaDTO> listar() {
        // 1️ Obtiene todas las reservas desde la BD (entidades)
        // 2️ Usa stream() para recorrerlos
        // 3️ Convierte cada Reserva a ReservaDTO
        // 4️ Las recopila en una lista de DTOs
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

        //Convertir entidad guardada a DTO
        ReservaDTO dto = modelMapper.map(guardada, ReservaDTO.class);
        dto.setId_cliente(guardada.getCliente().getId_cliente());
        return dto;
         //convierte la entidad guardada a DTO para devolverla. 
        //return modelMapper.map(guardada, ReservaDTO.class);
    }

    //Metodo para eliminar una reserva mediante su ID
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // Actualiza una reserva existente usando DTO
    public ReservaDTO actualizar(Long id, ReservaDTO reservaDTO) {
        // Busca el cliente por ID, si no existe lanza excepción
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Cliente cliente = clienteRepository.findById(reservaDTO.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Mesa mesa = mesaRepository.findById(reservaDTO.getIdMesa())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));


        // Actualiza los campos con la info del DTO
        reserva.setFechaReservada(reservaDTO.getFechaReservada());
        reserva.setPersonas(reservaDTO.getPersonas());
        reserva.setEstadoReserva(reservaDTO.getEstadoReserva());
        reserva.setTurno(reservaDTO.getTurno());
        reserva.setMesa(mesa);
        reserva.setCliente(cliente);


        // Guarda los cambios en la base
        Reserva actualizado = repository.save(reserva);

        // Devuelve el resultado como DTO
        ReservaDTO dto = modelMapper.map(actualizado, ReservaDTO.class);
        dto.setId_cliente(actualizado.getCliente().getId_cliente());
        return dto;

    }

        // Metodo para consultar mesas disponibles
        public List<MesaDTO> consultarMesasDisponibles(LocalDateTime fechaReservada, String turno) {
            List<Mesa> libres = mesaRepository.findMesasDisponibles(fechaReservada, turno);

            return libres.stream()
                    .map(m -> new MesaDTO(
                            m.getIdMesa(),
                            m.getNum_mesa(),
                            m.getCapacidad(),
                            m.getUbicacion(),
                            m.getEstadoMesa()
                    ))
                    .collect(Collectors.toList());
    }


}
