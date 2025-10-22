package com.apireserva.service;

import org.springframework.stereotype.Service; // Indico a Spring que este módulo es de servicio
import java.util.List;
import com.apireserva.model.Reserva;
import com.apireserva.repository.Reservarepository;

@Service // Especifico que este es el servicio
public class ReservaService {

    private final Reservarepository repository; // Inyección del atributo repository, para que hable con la base de datos. 

    public ReservaService(Reservarepository repository) {
        this.repository = repository;
    }

    public List<Reserva> listar() {
        return repository.findAll(); // findAll obtiene todas las reservas
    }

    public Reserva guardar(Reserva reserva) {
        return repository.save(reserva); 
    }

    public void eliminar(Long id) {
        repository.deleteById(id); 
    }
}
