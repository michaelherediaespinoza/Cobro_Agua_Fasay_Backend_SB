package coac.fasay.cobroagua.Services;

import coac.fasay.cobroagua.Repository.ReciboRepository;
import coac.fasay.cobroagua.entity.Cliente;
import coac.fasay.cobroagua.entity.Recibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;

    public Integer getMaxNumeroRecibo() {
        return reciboRepository.findMaxNumeroRecibo();
    }

    public Recibo createRecibo(Recibo recibo) {
        return reciboRepository.save(recibo);
    }

    public double sumaTotalReciboFechaActualAdmin(Date fechaActual) {
        return reciboRepository.sumaTotalReciboFechaActualAdmin(fechaActual);
    }

    public double sumaTotalReciboFechaActualCajero(Date fechaActual, String user) {
        return reciboRepository.sumaTotalReciboFechaActualCajero(fechaActual, user);
    }

    public double sumaTotalReciboFechaInicioFinUsuarioAdmin(Date fechaInicio , Date fechaFin, String user) {
        return reciboRepository.sumaTotalReciboFechaInicioFinUsuarioAdmin(fechaInicio, fechaFin, user);
    }

    public List<Object[]> getRecibosBetweenDatesAdmin(Date fechainicio, Date fechafin) {
        return reciboRepository.findRecibosBetweenDatesAdmin(fechainicio, fechafin);
    }

    public List<Object[]> getRecibosBetweenDatesCajero(Date fechainicio, Date fechafin, String user) {
        return reciboRepository.findRecibosBetweenDatesCajero(fechainicio, fechafin, user);
    }
}