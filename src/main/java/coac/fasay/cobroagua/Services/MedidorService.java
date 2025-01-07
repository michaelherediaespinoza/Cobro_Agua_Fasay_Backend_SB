package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.MedidorRepository;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Medidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedidorService {

    @Autowired
    private MedidorRepository medidorRepository;


    public List<Medidor> getMedidores() {
        return medidorRepository.findAll();
    }

    public Medidor save(Medidor medidor) {return  medidorRepository.save(medidor);
    }

    public Medidor findById(Integer id) {
        return medidorRepository.findById(id).get();
    }

    public void delete(Medidor medidor) {
        medidorRepository.delete(medidor);
    }
}
