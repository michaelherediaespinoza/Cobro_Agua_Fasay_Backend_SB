package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.ClienteRepository;
import coac.fasay.cobroagua.Repository.ReciboRepository;
import coac.fasay.cobroagua.Repository.UsuarioRepository;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public  UsuarioService (UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public ArrayList<Usuario> getAllUsers() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

        /*
    public Usuario getUsersById(Integer codigo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(codigo);
        return optionalUsuario.get();
    }
         */

    public Optional<Usuario> getUserById(Integer codigo) {
        return usuarioRepository.findById(codigo);
    }

    public Usuario getUserByUser(String user) {
        return usuarioRepository.findByUsername(user);
    }

}
