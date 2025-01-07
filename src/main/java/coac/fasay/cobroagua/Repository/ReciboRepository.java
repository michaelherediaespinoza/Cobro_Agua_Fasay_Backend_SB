package coac.fasay.cobroagua.Repository;

import coac.fasay.cobroagua.entity.Cobro;
import coac.fasay.cobroagua.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Integer> {

    @Query(value = "SELECT MAX(rec_num_recibo) FROM tbl_recibo", nativeQuery = true)
    public Integer numeroMaxRecibo();

    @Query(value = "SELECT MAX(rec_num_recibo) FROM tbl_recibo", nativeQuery = true)
    public Integer findMaxNumeroRecibo();

    @Query(value =  "SELECT SUM(cob.cob_total) " +
                    "FROM tbl_recibo rec " +
                    "JOIN tbl_cobro cob ON rec.cob_codigo = cob.cob_codigo " +
                    "WHERE CAST(rec.rec_fecha_hora AS date) = :fecha", nativeQuery = true)
    public double sumaTotalReciboFechaActualAdmin(@Param("fecha") Date fecha);


    @Query(value =  "SELECT SUM(cob.cob_total) " +
            "FROM tbl_recibo rec " +
            "JOIN tbl_cobro cob ON rec.cob_codigo = cob.cob_codigo " +
            "JOIN tbl_usuario usu ON rec.usu_codigo = usu.usu_codigo " +
            "WHERE CAST(rec.rec_fecha_hora AS date) = :fecha " +
            "AND usu.usu_user = :user", nativeQuery = true)
    public double sumaTotalReciboFechaActualCajero(@Param("fecha") Date fecha, @Param("user") String user);


    @Query(value =  "SELECT SUM(cob.cob_total) " +
            "FROM tbl_recibo rec " +
            "JOIN tbl_cobro cob ON rec.cob_codigo = cob.cob_codigo " +
            "JOIN tbl_usuario usu ON rec.usu_codigo = usu.usu_codigo " +
            "WHERE CAST(rec.rec_fecha_hora AS date) BETWEEN :fechainicio AND :fechafin " +
            "AND usu.usu_user = :user", nativeQuery = true)
    public double sumaTotalReciboFechaInicioFinUsuarioAdmin(@Param("fechainicio") Date fechainicio,
                                                            @Param("fechafin") Date fechafin,
                                                            @Param("user") String user);

    @Query(value =  "SELECT rec.rec_num_recibo, " +
            "rec.rec_fecha_hora, " +
            "cli.cli_cedula, " +
            "cli.cli_nombre, " +
            "cli.cli_apellido, " +
            "med.med_numero, " +
            "cob.cob_fecha, " +
            "usu.usu_user, " +
            "cob.cob_total " +
            "FROM tbl_recibo rec " +
            "JOIN tbl_cobro cob ON rec.cob_codigo = cob.cob_codigo " +
            "JOIN tbl_usuario usu ON rec.usu_codigo = usu.usu_codigo " +
            "JOIN medidor med ON cob.med_codigo = med.med_codigo " +
            "JOIN cliente cli ON med.cli_codigo = cli.cli_codigo " +
            "WHERE CAST(rec.rec_fecha_hora AS date) BETWEEN :fechainicio AND :fechafin"
            , nativeQuery = true)
    List<Object[]> findRecibosBetweenDatesAdmin(@Param("fechainicio") Date fechainicio, @Param("fechafin") Date fechafin);


    @Query(value =  "SELECT rec.rec_num_recibo, " +
            "rec.rec_fecha_hora, " +
            "cli.cli_cedula, " +
            "cli.cli_nombre, " +
            "cli.cli_apellido, " +
            "med.med_numero, " +
            "cob.cob_fecha, " +
            "cob.cob_total " +
            "FROM tbl_recibo rec " +
            "JOIN tbl_cobro cob ON rec.cob_codigo = cob.cob_codigo " +
            "JOIN tbl_usuario usu ON rec.usu_codigo = usu.usu_codigo " +
            "JOIN medidor med ON cob.med_codigo = med.med_codigo " +
            "JOIN cliente cli ON med.cli_codigo = cli.cli_codigo " +
            "WHERE CAST(rec.rec_fecha_hora AS date) BETWEEN :fechainicio AND :fechafin " +
            "AND usu.usu_user = :user", nativeQuery = true)
    List<Object[]> findRecibosBetweenDatesCajero(@Param("fechainicio") Date fechainicio,
                                                 @Param("fechafin") Date fechafin,
                                                 @Param("user") String user);





}


