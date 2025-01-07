package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="medidor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medidor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "med_codigo")
    private int codigo;

    @Column(name = "med_numero")
    private String numero;

    @Column(name = "med_tipo")
    private String tipo;

    @Column(name = "med_estatus")
    private String estatus;

    @Column(name = "med_fechaIngreso")
    private Date fechaIngreso;

    //@ManyToOne
    //@PrimaryKeyJoinColumn
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // cascade=CascadeType.ALL
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cli_codigo", nullable = false)
    private Cliente cliente;


}
