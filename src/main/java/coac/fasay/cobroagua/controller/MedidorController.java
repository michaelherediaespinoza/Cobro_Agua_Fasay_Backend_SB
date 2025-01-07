package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.MedidorService;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Medidor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/medidor")
@RequiredArgsConstructor
public class MedidorController {


    @Autowired
    private MedidorService medidorService;


    // Build Get All Users REST API
    @GetMapping("/medres")
    public ResponseEntity<List<Medidor>> getAllMedidores() {
        List<Medidor> medidores = medidorService.getMedidores();
        return new ResponseEntity<>(medidores, HttpStatus.OK);
    }

    /*
    @GetMapping("/medidores")
    public ResponseEntity<?> getUser() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Medidor> medidorList = medidorService.getMedidores();
        if (!medidorList.isEmpty()) {
            map.put("status", 1);
            map.put("data", medidorList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

      */

    @PostMapping("/save1")
    public ResponseEntity<?> saveMedidor(@RequestBody Medidor medidor) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            medidorService.save(medidor);
            map.put("status", 1);
            map.put("message", "Medidor GUARDADO Satisfactoriamente!");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            map.clear();
            map.put("status", 0);
            map.put("message", ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Medidor> createMedidor(@RequestBody Medidor medidor) {
        Medidor saveMedidor = medidorService.save(medidor);
        return new ResponseEntity<>(saveMedidor, HttpStatus.CREATED);
    }

    @GetMapping("/getmedporcodigo/{codigo}")
    public ResponseEntity<Medidor> getUserById(@PathVariable("codigo") Integer codigo) {
        Medidor medidor = medidorService.findById(codigo);
        return new ResponseEntity<>(medidor, HttpStatus.OK);
    }

    /*
    @GetMapping("/getmedporcodigo/{codigo}")
    public ResponseEntity<?> getMedidorById(@PathVariable Integer codigo) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Medidor medidor = medidorService.findById(codigo);
            map.put("status", 1);
            map.put("data", medidor);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

     */

    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer codigo) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Medidor medidor = medidorService.findById(codigo);
            medidorService.delete(medidor);
            map.put("status", 1);
            map.put("message", "Medidor " + medidor.getNumero() + " ELIMINADO satisfactoriamente!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{codigo}")
    public ResponseEntity<?> updateUserById(@PathVariable Integer codigo, @RequestBody Medidor medidorDetail) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Medidor medidorUpdate = medidorService.findById(codigo);
            medidorUpdate.setNumero(medidorDetail.getNumero());
            medidorUpdate.setTipo(medidorDetail.getTipo());
            medidorUpdate.setEstatus(medidorDetail.getEstatus());
            medidorService.save(medidorUpdate);
            map.put("status", 1);
            map.put("data", medidorUpdate);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
