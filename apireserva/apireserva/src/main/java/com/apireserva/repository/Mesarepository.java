package com.apireserva.repository;

import com.apireserva.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

// Esta parte permite que se puedan realizar todas las consultas HTTP o CRUD en Mesa
public interface Mesarepository extends JpaRepository<Mesa, Long> {
   //permite consultar solo las mesas que estan disponibles
    @Query("SELECT m FROM Mesa m " +
            "WHERE m.estadoMesa = 'Disponible' " +
            "AND NOT EXISTS (" +
            "   SELECT 1 FROM Reserva r " +
            "   WHERE r.mesa = m " +
            "   AND r.fechaReservada = :fechaReservada " +
            "   AND r.turno = :turno " +
            "   AND r.estadoReserva IN ('Confirmada', 'Pendiente')" +
            ")")
    List<Mesa> findMesasDisponibles(@Param("fechaReservada") LocalDateTime fechaReservada,
                                    @Param("turno") String turno);
}
