package coac.fasay.cobroagua.Repository;

import coac.fasay.cobroagua.entity.Medidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidorRepository extends JpaRepository<Medidor, Integer> {


}
