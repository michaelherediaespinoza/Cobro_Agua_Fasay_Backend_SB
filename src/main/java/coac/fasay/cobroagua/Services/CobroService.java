package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.CobroRepository;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Cobro;
import coac.fasay.cobroagua.entity.Medidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CobroService {

    @Autowired
    private CobroRepository cobroRepository;

    public Cobro save(Cobro cobro) {return cobroRepository.save(cobro);
    }
    public Cobro getCobroById(int codigo) {
        Optional<Cobro> optionalCobro = cobroRepository.findById(codigo);
        return optionalCobro.get();
    }
    public List<Cobro> getCobros() {
        return cobroRepository.findAll();
    }

    public Cobro findById(Integer id) {
        return cobroRepository.findById(id).get();
    }

    public void delete(Cobro cobro) {
        cobroRepository.delete(cobro);
    }



    public List<Cobro> getCobrosPendientes() {
        return cobroRepository.findByCobrosPendientesAll();
    }
    public List<Cobro> getCobrosPendientePorCedula(String cedula) {
        return cobroRepository.findByCobroPendientePorCedula(cedula);
    }
    public List<Cobro> getCobrosPendientePorNombre(String nombre) {
        return cobroRepository.findByCobroPendientePorNombre(nombre);
    }

    public Cobro updateCobroAll(Cobro cobro) {
        Cobro existingCobro = cobroRepository.findById(cobro.getCob_codigo()).get();
        existingCobro.setCob_fecha(cobro.getCob_fecha());
        existingCobro.setCob_valorActual(cobro.getCob_valorActual());
        existingCobro.setCob_mul_trabajo(cobro.getCob_mul_trabajo());
        existingCobro.setCob_mul_reunion(cobro.getCob_mul_reunion());
        existingCobro.setCob_mul_desfile(cobro.getCob_mul_desfile());
        existingCobro.setCob_cambioMedidor(cobro.getCob_cambioMedidor());
        existingCobro.setCob_nuevoSocio(cobro.getCob_nuevoSocio());
        existingCobro.setCob_insumo(cobro.getCob_insumo());
        existingCobro.setCob_otros(cobro.getCob_otros());
        existingCobro.setCob_total(cobro.getCob_total());
        existingCobro.setCob_estado(cobro.getCob_estado());
        Cobro updateCobroAll = cobroRepository.save(existingCobro);
        return updateCobroAll;
    }

    public Cobro updateCoborEstado(Cobro cobro) {
        Cobro existingCobro = cobroRepository.findById(cobro.getCob_codigo()).get();
        existingCobro.setCob_estado(cobro.getCob_estado());
        Cobro updatedCobro = cobroRepository.save(existingCobro);
        return updatedCobro;
    }

    public List<Object[]> findCobrosPendientesBetweenDatesAdmin(Date fechainicio, Date fechafin) {
        return cobroRepository.findCobrosPendientesBetweenDatesAdmin(fechainicio, fechafin);
    }



}
