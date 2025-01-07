package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="TBL_EMPRESA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_codigo")
    private int codigo;

    @Column(name = "emp_nombre")
    private String nombre;

    @Column(name = "emp_direccion")
    private String direccion;

    @Column(name = "emp_telefono")
    private String telefono;
}
