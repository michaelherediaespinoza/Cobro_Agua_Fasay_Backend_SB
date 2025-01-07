package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name="TBL_ROL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_codigo")
    private int codigo;

    @Column(name = "rol_nombre", unique = true)
    private String nombre;

}
