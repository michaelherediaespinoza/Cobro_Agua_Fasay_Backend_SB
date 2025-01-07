package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.RolService;
import coac.fasay.cobroagua.entity.Rol;
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
@RequestMapping("/api/rol")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/allroles")
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.getRoles();
        if (roles != null) {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
