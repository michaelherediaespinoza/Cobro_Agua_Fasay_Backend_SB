package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.ClienteService;
import coac.fasay.cobroagua.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /*
    @PostMapping("/save")
    public ResponseEntity<ResponseEntity<Cliente>> saveCliente(@RequestBody Cliente cliente) {
        ResponseEntity<Cliente> newCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(newCliente);
    }


    // build create User REST API
    @PostMapping("/save")
    public ResponseEntity<Cliente> createUser2(@RequestBody Cliente cliente) {
        try {
            Cliente savedCliente = clienteService.createCliente(cliente);
            return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

      */

    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody Cliente cliente) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            clienteService.createCliente(cliente);
            map.put("status", 1);
            map.put("message", "Cliente GUARDADO Satisfactoriamente!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            map.clear();
            map.put("status", 0);
            map.put("message", ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }



    // Build Update User REST API
    @PutMapping("/update/{codigo}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer codigo,
                                                 @RequestBody Cliente cliente) {
        cliente.setCodigo(codigo);
        Cliente dataCliente = clienteService.getClienteById(codigo);
        if (dataCliente != null) {
            Cliente updatedCliente = clienteService.updateUser(dataCliente);
            return ResponseEntity.ok(updatedCliente);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
/*
    // Get all clientes
    @GetMapping("/allclientes")
    public ArrayList<Cliente> getAllCliente(){
        ArrayList<Cliente> Clientes =  clienteService.getAllCliente();
        return Clientes;
    }
    */

    // Build Get All Users REST API
    @GetMapping("/allclientes")
    public ResponseEntity<List<Cliente>> getAllUsers() {
        List<Cliente> clientes = clienteService.getAllClientes();
        if (clientes != null) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // build get cliente by id REST API
    // http://localhost:8080/api/cliente/1
    @GetMapping("/getcliporcodigo/{codigo}")
    public ResponseEntity<Cliente> getUserById(@PathVariable("codigo") Integer codigo) {
        Cliente cliente = clienteService.getClienteById(codigo);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteUser(@PathVariable("codigo") Integer codigo) {
        // clienteService.deleteCliente(codigo);
        // return new ResponseEntity<>("Cliente eliminado correctamente!", HttpStatus.OK);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            clienteService.deleteCliente(codigo);
            map.put("status", 1);
            map.put("message", "Cliente is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }


}
