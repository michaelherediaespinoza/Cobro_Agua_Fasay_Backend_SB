package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.UsuarioService;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Cobro;
import coac.fasay.cobroagua.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody Usuario usuario) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            usuarioService.saveUser(usuario);
            map.put("status", 1);
            map.put("message", "Usuario GUARDADO Satisfactoriamente!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            map.clear();
            map.put("status", 0);
            map.put("message", ex.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

        /*
        @GetMapping("/getusuariobycodigo/{codigo}")
        public ResponseEntity<Usuario> getUsuarioById(@PathVariable("codigo") Integer codigo) {
            Usuario usuario = usuarioService.getUsersById(codigo);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }

         */

    @GetMapping("/getusuariobycodigo/{codigo}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("codigo") Integer codigo) {
        Optional<Usuario> usuarioOpt = usuarioService.getUserById(codigo);
        if (usuarioOpt.isPresent()) {
            return new ResponseEntity<>(usuarioOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyusu/{usuario}")
    public ResponseEntity<Usuario> getUsuarioByUsuario(@PathVariable("usuario") String user) {
        Usuario usuarioOpt = usuarioService.getUserByUser(user);
        if (usuarioOpt != null) {
            return new ResponseEntity<>(usuarioOpt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Build Get All Users REST API
    @GetMapping("/getallusuarios")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = usuarioService.getAllUsers();
        if (usuarios != null) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
