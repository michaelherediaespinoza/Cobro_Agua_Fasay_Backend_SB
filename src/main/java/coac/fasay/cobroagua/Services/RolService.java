package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.RolRepository;
import coac.fasay.cobroagua.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public ArrayList<Rol> getRoles () {
        return (ArrayList<Rol>) rolRepository.findAll();
    }
}
