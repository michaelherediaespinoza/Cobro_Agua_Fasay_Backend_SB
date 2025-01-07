package coac.fasay.cobroagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="TBL_CARGO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cargo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_codigo")
    private int codigo;

    @Column(name = "car_nombre")
    private String nombre;

    @Column(name = "car_descripcion")
    private String descripcion;

}
