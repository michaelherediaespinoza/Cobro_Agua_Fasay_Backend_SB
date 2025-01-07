package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.AgenciaService;
import coac.fasay.cobroagua.entity.Agencia;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agencia")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping("/allagencias")
    public ResponseEntity<List<Agencia>> getAllAgencias() {
        List<Agencia> agencias = agenciaService.getAgencias();
        if (agencias != null) {
            return new ResponseEntity<>(agencias, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
