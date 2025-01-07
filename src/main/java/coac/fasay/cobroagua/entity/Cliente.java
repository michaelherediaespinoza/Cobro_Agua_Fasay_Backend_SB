package coac.fasay.cobroagua.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cli_codigo")
    private int codigo;

    @Column(name = "cli_cedula", unique = true)
    private String cedula;

    @Column(name = "cli_nombre")
    private String nombre;

    @Column(name = "cli_apellido")
    private String apellido;

    @Column(name = "cli_direccion")
    private String direccion;

    @Column(name = "cli_telefono")
    private String telefono;

    @Column(name = "cli_fechaIngreso")
    private Date fechaIngreso;

    @Column(name = "cli_sexo")
    private String sexo;


}
