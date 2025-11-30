package com.apireserva.service;

//import lombok.AllArgsConstructor; // genera constructores para todo tipo de clase. 
import lombok.RequiredArgsConstructor; //genera lombok constructores automaticos. 
import java.util.List;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper; // facilita la conversion automatica entre entidades y DTO. 
import java.util.stream.Collectors; // Se usa para recopilar los resultados del stream en una lista, permitiendo transformar cada entidad en DTO


import com.apireserva.model.Cliente;
import com.apireserva.dto.ClienteDTO; //DTO
import com.apireserva.repository.Clienterepository;

@Service
@RequiredArgsConstructor //lombok genera  un constructor para campos final o anotaciones @NONNULL. 
public class ClienteService {

    private final Clienterepository repository;  // Inyección del atributo repository, para que hable con la base de datos. 

    private final ModelMapper modelMapper;  //Inyeccion ModelMapper para convertir facilmente entre cliente a clienteDTO.

    //modificacion del metodo, devuelve una lista de DTO, no de entidades. por que no devuelve directamente los datos de MODEL. 
    public List<ClienteDTO> listar() {
        // 1️ Obtiene todos los clientes desde la BD (entidades)
        // 2️ Usa stream() para recorrerlos
        // 3️ Convierte cada Cliente a ClienteDTO
        // 4️ Los recopila en una lista de DTOs
        return repository.findAll() //le digo a repo que obtenga todos los registros de la base de datos y los regresa con el metodo findall, indica encontrar todos los registros. 
                .stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList()); 
    } 

    //Metodo para buscar a un cliente por su id
    public ClienteDTO obtenerPorId(Long id) {
    return repository.findById(id)
            .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
            .orElse(null);
}



    //Este metodo guarda un cliente mediante ClienteDTO
    public ClienteDTO guardar(ClienteDTO clienteDTO) { //Cliente = tipo de dato (clase instanciada en model) , cliente = tipo de parametro (variable)

        //--Convierte el DTO recibido en una entidad Cliente (para poder guardarla)
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        //--Guarda la entidad con el repositorio JPA
        Cliente guardado = repository.save(cliente);
        //--Convierte nuevamente el resultado (entidad) a DTO para devolverlo al controlador
        return modelMapper.map(guardado, ClienteDTO.class);
    }



    public void eliminar(Long id) { // long significa la capacidad de longitud del id (tipo de dato), ID es lo que se necesita para eliminar
        repository.deleteById(id); // Metodo Borrado por id.

    }

    // Actualiza un cliente existente usando DTO
    public ClienteDTO actualizar(Long id, ClienteDTO clienteDTO) {
        // Busca el cliente por ID, si no existe lanza excepción
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Actualiza los campos con la info del DTO
        cliente.setNombre_cliente(clienteDTO.getNombre_cliente());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setTelefono(clienteDTO.getTelefono());

        // Guarda los cambios en la base
        Cliente actualizado = repository.save(cliente);

        // Devuelve el resultado como DTO
        return modelMapper.map(actualizado, ClienteDTO.class);
    }
} 
