package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.CobroService;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Cobro;
import coac.fasay.cobroagua.entity.Medidor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/cobro")
@RequiredArgsConstructor
public class CobroController {

    @Autowired
    private CobroService cobroService;

    @PostMapping("/savecobro")
    public ResponseEntity<?> saveCobro(@RequestBody Cobro cobro) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            cobroService.save(cobro);
            map.put("status", 1);
            map.put("message", "Cobro GUARDADO Satisfactoriamente!");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }catch (Exception ex) {
                map.clear();
                map.put("status", 0);
                map.put("message", "Data is not found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allcobros")
    public ResponseEntity<List<Cobro>> getAllCobros() {
        List<Cobro> cobros = cobroService.getCobros();
        return new ResponseEntity<>(cobros, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<?> deleteCobro(@PathVariable Integer codigo) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Cobro cobro = cobroService.findById(codigo);
            cobroService.delete(cobro);
            map.put("status", 1);
            map.put("message", "COBRO ELIMINADO SATISFACTORIAMENTE!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found " + ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    /*
    @PutMapping("/update/{codigo}")
    public ResponseEntity<?> updateCobroById(@PathVariable Integer codigo, @RequestBody Medidor medidorDetail) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Cobro medidorUpdate = medidorService.findById(codigo);
            medidorUpdate.setNumero(medidorDetail.getNumero());
            medidorUpdate.setTipo(medidorDetail.getTipo());
            medidorUpdate.setEstatus(medidorDetail.getEstatus());
            medidorService.save(medidorUpdate);
            map.put("status", 1);
            map.put("data", medidorService.findById(codigo));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

     */

    @GetMapping("/getcobporcodigo/{codigo}")
    public ResponseEntity<Cobro> getCobroById(@PathVariable("codigo") Integer codigo) {
        Cobro cobro = cobroService.findById(codigo);
        return new ResponseEntity<>(cobro, HttpStatus.OK);
    }

    @GetMapping("/getcobpornombrecliente/{nombre}")
    public ResponseEntity<?> getCobroByNombreCliente(@PathVariable("nombre") String nombre) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Cobro> cobros = cobroService.getCobrosPendientePorNombre(nombre);
        if (cobros == null) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cobros, HttpStatus.OK);
    }


    // ----------------API PARA------REPORTES------------------------

    @GetMapping("/getcobporcedulacliente/{cedula}")
    public ResponseEntity<List<Cobro>> getCobroByCedulaCliente(@PathVariable("cedula") String cedula) {
        List<Cobro> cobros = cobroService.getCobrosPendientePorCedula(cedula);
        return new ResponseEntity<>(cobros, HttpStatus.OK);
    }

    @GetMapping("/allcobrospendientes")
    public ResponseEntity<List<Cobro>> getAllCobrosPendientes() {
        List<Cobro> cobros = cobroService.getCobrosPendientes();
        return new ResponseEntity<>(cobros, HttpStatus.OK);
    }

    @GetMapping("/allcobpornombrecliente")
    public ResponseEntity<List<Cobro>> findByCobroPendientePorCedula() {
        List<Cobro> cobros = cobroService.getCobrosPendientes();
        return new ResponseEntity<>(cobros, HttpStatus.OK);
    }

    @GetMapping("/allcobrospendientesfechas/{fechainicio}/{fechafin}")
    public ResponseEntity<List<Object[]>> getCobrosPendientesAdmin(@PathVariable("fechainicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechainicio,
                                                          @PathVariable("fechafin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechafin) {
        List<Object[]> cobrospendientes = cobroService.findCobrosPendientesBetweenDatesAdmin(fechainicio, fechafin);
        return ResponseEntity.ok(cobrospendientes);
    }



    //  UPDATE ESTADO DE (P) Pendinte a (C) Cobrado
    @PutMapping("/update/{codigo}")
    public ResponseEntity<Cobro> updateCobroEstado(@PathVariable int codigo, @RequestBody Cobro cobro) {
        cobro.setCob_codigo(codigo);
        Cobro dataCobro = cobroService.getCobroById(codigo);
        if (dataCobro != null) {
            dataCobro.setCob_estado(cobro.getCob_estado());
            Cobro updatedCobro = cobroService.updateCoborEstado(dataCobro);
            return ResponseEntity.ok(updatedCobro);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/updateall/{codigo}")
    public ResponseEntity<?> updateCobro(@PathVariable int codigo, @RequestBody Cobro cobro) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            cobro.setCob_codigo(codigo);
            Cobro dataCobro = cobroService.getCobroById(codigo);
            cobroService.updateCobroAll(dataCobro);
            map.put("status", 1);
            map.put("data", "COBRO ACTUALIZADO SATISFACTORIAMENTE");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found " + ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
