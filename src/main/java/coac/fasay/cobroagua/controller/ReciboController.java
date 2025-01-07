package coac.fasay.cobroagua.controller;

import coac.fasay.cobroagua.Services.ReciboService;
import coac.fasay.cobroagua.entity.Cobro;
import coac.fasay.cobroagua.entity.Recibo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://192.168.88.61:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/recibo")
@RequiredArgsConstructor
public class ReciboController {

    @Autowired
    private ReciboService reciboService;

    @GetMapping("/max-numero")
    public Integer getMaxNumeroRecibo() {
        return reciboService.getMaxNumeroRecibo();
    }
    @GetMapping("/max-numero-recibo")
    public ResponseEntity<Integer> getMaxNumeroRecibo2() {
        Integer maxNumeroRecibo = reciboService.getMaxNumeroRecibo();
        return new ResponseEntity<>(maxNumeroRecibo, HttpStatus.OK);
    }

    @PostMapping("/saverecibo")
    public ResponseEntity<?> saveRecibo(@RequestBody Recibo recibo) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        //System.out.println("hola ACTUAL: " +   recibo.getFechaHora());

        try {
            // Validación de nulo para la fecha y hora
            if (recibo.getFechaHora() == null) {
                map.put("status", 0);
                map.put("message", "La fecha y hora no puede ser nula.");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }

            LocalDateTime fechaHora = recibo.getFechaHora();
        // Obtener la zona horaria del sistema local
            ZoneId localZoneId = ZoneId.systemDefault();
        // Convertir LocalDateTime a ZonedDateTime en la zona horaria UTC
            ZonedDateTime zonedDateTimeUTC = fechaHora.atZone(ZoneId.of("UTC"));
        // Convertir ZonedDateTime a la zona horaria local
            ZonedDateTime zonedDateTimeLocal = zonedDateTimeUTC.withZoneSameInstant(localZoneId);

        // Formatear la fecha y hora en la zona horaria local
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formattedDateTime = zonedDateTimeLocal.format(formatter);
        //System.out.println("Actual final: " +  formattedDateTime);
            LocalDateTime fechaHora2 = LocalDateTime.parse(formattedDateTime, formatter);

            recibo.setFechaHora(fechaHora2);
            reciboService.createRecibo(recibo);

            map.put("status", 1);
            map.put("message", "Recibo is Saved Successfully!");
            return new ResponseEntity<>(map, HttpStatus.CREATED);

        } catch (DateTimeParseException e) {
            // Manejo de errores de formato de fecha
            map.put("status", 0);
            map.put("message", "Error al formatear la fecha y hora: " + e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejo de cualquier otro tipo de excepción
            map.put("status", 0);
            map.put("message", "Error al guardar el recibo: " + e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     /*
        Este WS genera un reporte del total de cobros realizados de una determina fecha
     */
    @GetMapping("/getcuadresumafechaactual/{fechaactual}")
    public ResponseEntity<Double> getCuadreSumaFechaActual(@PathVariable("fechaactual") Date fechaActual) {
        double cobroTotalActual = reciboService.sumaTotalReciboFechaActualAdmin(fechaActual);

        if (Double.isNaN(cobroTotalActual)) {
            // Manejar el caso donde cobroTotalActual es null
            System.out.println("El valor de cobroTotalActual es null");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Por ejemplo, devolver un 404 Not Found
        }
        System.out.println("data: " + cobroTotalActual);
        return new ResponseEntity<>(cobroTotalActual, HttpStatus.OK);
    }

    @GetMapping("/getcuadresumafechaactualcajero/{fechaactual}/{user}")
    public ResponseEntity<Double> getCuadreSumaFechaActual(@PathVariable("fechaactual") Date fechaActual,
                                                            @PathVariable("user") String user) {
        double cobroTotalActual = reciboService.sumaTotalReciboFechaActualCajero(fechaActual, user);

        if (Double.isNaN(cobroTotalActual)) {
            // Manejar el caso donde cobroTotalActual es null
            System.out.println("El valor de cobroTotalActual es null");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Por ejemplo, devolver un 404 Not Found
        }
        System.out.println("data: " + cobroTotalActual);
        return new ResponseEntity<>(cobroTotalActual, HttpStatus.OK);
    }

    @GetMapping("/getsumafechainiciofinusuarioadmin/{fechainicio}/{fechafin}/{user}")
    public ResponseEntity<Double> getCuadreSumaFechaInicioFinUsuarioAdmin(@PathVariable("fechainicio") Date fechaInicio,
                                                                          @PathVariable("fechafin") Date fechaFin,
                                                                          @PathVariable("user") String user) {
        double cobroTotalActual = reciboService.sumaTotalReciboFechaInicioFinUsuarioAdmin(fechaInicio, fechaFin, user);

        if (Double.isNaN(cobroTotalActual)) {
            // Manejar el caso donde cobroTotalActual es null
            //System.out.println("El valor de cobroTotalActual es null");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Por ejemplo, devolver un 404 Not Found
        }
        //System.out.println("data: " + cobroTotalActual);
        return new ResponseEntity<>(cobroTotalActual, HttpStatus.OK);
    }

    /*
    @GetMapping("/data-all-porfechas/{fechainicio}/{fechafin}")
    public List<Object[]> getRecibos2(@PathVariable("fechainicio") Date fechainicio, @PathVariable("fechafin") Date fechafin) {
        return reciboService.getRecibosBetweenDates(fechainicio, fechafin);
    }
    */

    @GetMapping("/data-all-porfechas/{fechainicio}/{fechafin}")
    public ResponseEntity<List<Object[]>> getRecibosAdmin(@PathVariable("fechainicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechainicio,
                                     @PathVariable("fechafin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechafin) {
        List<Object[]> recibos = reciboService.getRecibosBetweenDatesAdmin(fechainicio, fechafin);
        return ResponseEntity.ok(recibos);
    }

    @GetMapping("/data-all-porfechas-cajero/{fechainicio}/{fechafin}/{user}")
    public ResponseEntity<List<Object[]>> getRecibosCajero(@PathVariable("fechainicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechainicio,
                                           @PathVariable("fechafin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechafin,
                                           @PathVariable("user") String user) {
        List<Object[]> recibos = reciboService.getRecibosBetweenDatesCajero(fechainicio, fechafin, user);
        return ResponseEntity.ok(recibos);
    }

}
