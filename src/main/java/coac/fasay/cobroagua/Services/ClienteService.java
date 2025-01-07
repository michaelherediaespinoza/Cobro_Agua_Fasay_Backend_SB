package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.ClienteRepository;
import coac.fasay.cobroagua.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public  ClienteService (ClienteRepository clienteRepository) {
        this .clienteRepository = clienteRepository;
    }

    /*
    // Guardar data
    public ResponseEntity<Cliente> saveCliente (Cliente cliente) {
        Cliente  nuevoCliente  = clienteRepository.save(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }
     */

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /*
    public Cliente createCliente1(Cliente cliente, String cedula) {
       Cliente cli = getClienteById(cliente.getCodigo());
        //if(cli.getCedula().equals(cedula)) {
            try {
                if(cli.getCedula().equals(cedula)) {

                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        return clienteRepository.save(cliente);
    }
    */

    public ArrayList<Cliente> getAllClientes() {
        return (ArrayList<Cliente>) clienteRepository.findAll();
    }

    public Cliente getClienteById(Integer codigo) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(codigo);
        return optionalCliente.get();
    }

    public Cliente updateUser(Cliente cliente) {
        Cliente existingCliente = clienteRepository.findById(cliente.getCodigo()).get();
        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setApellido(cliente.getApellido());
        existingCliente.setDireccion(cliente.getDireccion());
        existingCliente.setTelefono(cliente.getTelefono());
        existingCliente.setFechaIngreso(cliente.getFechaIngreso());
        existingCliente.setSexo(cliente.getSexo());
        Cliente updatedCliente = clienteRepository.save(existingCliente);
        return updatedCliente;
    }


    public void deleteCliente(Integer codigo) {
        clienteRepository.deleteById(codigo);
    }
}