package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="TBL_MULTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Multa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mul_codigo")
    private int codigo;

    @Column(name = "mul_trabajo")
    private String trabajo;

    @Column(name = "mul_reunion")
    private String reunion;

    @Column(name = "mul_desfile")
    private String desfile;

    @Column(name = "mul_fecha")
    private Date fecha;


    private Cliente cliente;
}
