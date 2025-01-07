package coac.fasay.cobroagua.Repository;

import coac.fasay.cobroagua.entity.Cobro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CobroRepository extends JpaRepository<Cobro, Integer> {

    @Query(value = "SELECT * FROM tbl_cobro co WHERE co.cob_estado ='P'", nativeQuery = true)
    List<Cobro> findByCobrosPendientesAll();

    @Query(value = "SELECT co.* FROM tbl_cobro co " +
            "JOIN medidor me ON co.med_codigo = me.med_codigo " +
            "JOIN cliente cl ON me.cli_codigo = cl.cli_codigo " +
            "WHERE  co.cob_estado = 'P' AND cl.cli_cedula = :cedula " +
            "ORDER BY co.cob_fecha;" , nativeQuery = true)
    List<Cobro> findByCobroPendientePorCedula(@Param("cedula") String cedula);



    @Query(value = "SELECT co.cob_estado, " +
            "co.cob_fecha, " +
            "co.cob_total, " +
            "me.med_numero, " +
            "cl.cli_nombre, " +
            "cl.cli_apellido "+
            "FROM tbl_cobro co " +
            "JOIN medidor me ON co.med_codigo = me.med_codigo " +
            "JOIN cliente cl ON me.cli_codigo = cl.cli_codigo " +
            "WHERE  co.cob_estado = 'P'" +
            "AND co.cob_fecha BETWEEN :fechainicio AND :fechafin " +
            "ORDER BY co.cob_fecha;"  , nativeQuery = true)
    List<Object[]> findCobrosPendientesBetweenDatesAdmin(@Param("fechainicio") Date fechainicio,
                                                         @Param("fechafin") Date fechafin);



            @Query(value = "SELECT co FROM tbl_cobro co " +
            "JOIN co.medidor me " +
            "JOIN me.cliente cl " +
            "WHERE co.estado = 'P' AND cl.nombre LIKE %:nombre%" , nativeQuery = true)
    List<Cobro> findByCobroPendientePorNombre(@Param("nombre") String nombre);


    /*

       @Query("SELECT co FROM Cobro c, Medidor m, Cliente c, " +
            "WHERE c.medidor.codigo = m.codigo AND " +
            "m.cliente.codigo = c.codigo AND c.estado = 'P'")
    List<Cobro> findByCobrosPendientesAll();


    @Query("SELECT * FROM Cobro c, Medidor m, Cliente c, " +
            "WHERE c.medidor.codigo = m.codigo AND " +
            "m.cliente.codigo = c.codigo AND c.estado = 'P' AND" +
            " c.cedula = :cedula")
    List<Cobro> findByCobroPendientePorCedula(@Param("cedula") String cedula);


    @Query("SELECT * FROM Cobro c, Medidor m, Cliente c, " +
            "WHERE c.medidor.codigo = m.condigo AND " +
            "m.cliente.codigo = c.codigo AND c.estado = 'P' AND" +
            " c.nombre LIKE %:nombre%")
    List<Cobro> findByCobroPendientePorNombre(@Param("nombre") String nombre);
     */

}
