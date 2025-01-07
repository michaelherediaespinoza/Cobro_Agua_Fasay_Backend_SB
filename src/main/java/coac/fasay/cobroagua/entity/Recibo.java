package coac.fasay.cobroagua.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="TBL_RECIBO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recibo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rec_codigo")
    private int codigo;

    @Column(name = "rec_fechaHora")
    private LocalDateTime fechaHora;

    @Column(name = "rec_numRecibo")
    private int numero;

    @Column(name = "rec_observacion")
    private String observacion;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cob_codigo", nullable = false)
    private Cobro cobro;

    //@OneToOne(fetch = FetchType.EAGER, optional = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_codigo", nullable = false)
    private Usuario usuario;
}
