package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="TBL_AGENCIA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "age_codigo")
    private int codigo;

    @Column(name = "age_direccion")
    private String direccion;

    @Column(name = "age_telefono")
    private String telefono;

    @Column(name = "age_tipo_servicio")
    private String tipo_servicio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "emp_codigo", nullable = false)
    private Empresa empresa;
}
