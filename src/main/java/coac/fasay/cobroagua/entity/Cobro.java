package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="TBL_COBRO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cobro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cob_codigo")
    private int cob_codigo;

    @Column(name = "cob_fecha")
    private Date cob_fecha;

    @Column(name = "cob_valorActual")
    private double cob_valorActual;

    @Column(name = "cob_mul_trabajo")
    private double cob_mul_trabajo;

    @Column(name = "cob_mul_reunion")
    private double cob_mul_reunion;

    @Column(name = "cob_mul_desfile")
    private double cob_mul_desfile;

    @Column(name = "cob_cambioMedidor")
    private double cob_cambioMedidor;

    @Column(name = "cob_newSocio")
    private double cob_nuevoSocio;

    @Column(name = "cob_insumo")
    private double cob_insumo;

    @Column(name = "cob_otros")
    private double cob_otros;

    @Column(name = "cob_total")
    private double cob_total;

    @Column(name = "cob_estado")
    private char cob_estado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "med_codigo", nullable = false)
    private Medidor medidor;
}
