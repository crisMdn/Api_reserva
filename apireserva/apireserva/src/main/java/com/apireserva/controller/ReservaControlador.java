package com.apireserva.controller; //es el paquete propio 

import org.springframework.web.bind.annotation.*; //da acceso a las anotaciones de spring
import java.util.List; //listas en java
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import com.apireserva.service.ReservaService;
import com.apireserva.dto.ReservaDTO;


@RestController //indica a sprint que esto es un controlador
@RequestMapping("/api/reservas") //indico al controlador cual es la ruta base, en este caso es reserva
@RequiredArgsConstructor //Lombok genera constructor con los campos final
public class ReservaControlador {
    
    private final ReservaService service; //inyecto la dependencia del servicio, la cual no cambiara

    //constructor sustituido por @Requered and import lombok. 


    @GetMapping //indica a sprint que este metodo manejara solicitudes especificadas de get
    public List<ReservaDTO> listar() { //me va a retornar la dependencia inyectada en service
        return service.listar();
    }

    @PostMapping //metodo que usara las solicitudes tipo POST, para nuevas reservas
    public ReservaDTO guardar(@RequestBody ReservaDTO reservaDTO) { //body toma el cuerpo de la solitud http y lo convierte en un objeto Java a partir de los datos JSON enviados
        return service.guardar(reservaDTO); 
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) //devuelve  un codigo de estado. 
    @DeleteMapping("/{id}") //esta solicitud necesaritara un id, si no; no funciona
    public void eliminar(@PathVariable Long id) { //Path vinculara el id de la url con el id de la bs para hacer la iliminacion
        service.eliminar(id); //long tipo de dato para representar tipo de datos largos
    } 
}      
