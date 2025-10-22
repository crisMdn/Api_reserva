package com.apireserva.service;

//import lombok.AllArgsConstructor; // genera constructores para todo tipo de clase. 
import lombok.RequiredArgsConstructor; //genera lombok constructores automaticos. 
import java.util.List;
import org.springframework.stereotype.Service;
import com.apireserva.model.Cliente;
import com.apireserva.repository.Clienterepository;

@Service
@RequiredArgsConstructor //lombok genera  un constructor para campos final o anotaciones @NONNULL. 
public class ClienteService {

    private final Clienterepository repository;  // Inyección del atributo repository, para que hable con la base de datos. 

    //public ClienteService(Clienterepository repository) { // constructor comentado por que usare la import lombok y su anotacion. 
      //  this.repository = repository;
    //}

    public List<Cliente> listar() {
        return repository.findAll(); //le digo a repo que obtenga todos los registros de la base de datos y los regresa con el metodo findall, indica encontrar todos los registros. 
    }

    public Cliente guardar(Cliente cliente) { //Cliente = tipo de dato (clase instanciada en model) , cliente = tipo de parametro (variable)
        return repository.save(cliente); //save es un metodo que indica guardar pasandole el parametro de la variable donde se guarda cliente. 
    }

    public void eliminar(Long id) { // long signica la capacidad de longitud del id (tipo de dato), ID es lo que se necesita para eliminar 
        repository.deleteById(id); // Metodo Borrado por id. 
    }
}
