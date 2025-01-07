package coac.fasay.cobroagua.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.sql.Date;
import java.util.List;

/**
 * Creacion de Usuario para el ingreso al sistema
 */
@Entity
@Table(name="TBL_USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_codigo")
    private int codigo;

    @Column(name = "usu_nombre")
    private String nombre;

    @Column(name = "usu_apellido")
    private String apellido;

    @Column(name = "usu_user", unique = true)
    private String user;

    @Column(name = "usu_password")
    private String password;

    @Column(name = "usu_estado")
    private char estado;

    @Column(name = "usu_fecha_creacion")
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "car_codigo")
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "age_codigo")
    private Agencia agencia;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    // Relación uno a muchos con Recibo
    //@JsonManagedReference
    @JsonBackReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Recibo> recibos;


}
