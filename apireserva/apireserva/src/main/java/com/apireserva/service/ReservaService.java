package com.apireserva.service;

import org.springframework.stereotype.Service; //Indico a Sprint que este modulo es de servicio
import java.util.List;
import com.apireserva.model.Reserva;
import com.apireserva.repository.ReservaRepository;


@Service //especifico que este es el servicio
public class ReservaService {

    private final ReservaRepository repository; //inyeccion del atributo repository

    public ReservaService(ReservaRepository repository){
        this.repository = repository;
    }

    public List<Reserva> listar() {
        return repository.findAll(); //findAll optengo todas las reservas 
    }

    public Reserva guardar(Reservar reserva) {
        return repository.save(reserva); 
    }

    public void eliminar(Long id) {
        repository.deleteByid(id); 
    }
    
}
