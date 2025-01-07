package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.CargoRepository;
import coac.fasay.cobroagua.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public ArrayList<Cargo> getAllCargos() {
        return (ArrayList<Cargo>) cargoRepository.findAll();
    }

}
