package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.CargoService;
import coac.fasay.cobroagua.entity.Cargo;
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
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/allcargos")
    public ResponseEntity<List<Cargo>> getCargos() {
        List<Cargo> cargos = cargoService.getAllCargos();
        if (cargos != null) {
            return new ResponseEntity<>(cargos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
