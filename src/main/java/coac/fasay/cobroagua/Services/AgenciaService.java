package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.AgenciaRepository;
import coac.fasay.cobroagua.entity.Agencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository agenciaRepository;

    public ArrayList<Agencia> getAgencias () {
        return (ArrayList<Agencia>) agenciaRepository.findAll();
    }
}
