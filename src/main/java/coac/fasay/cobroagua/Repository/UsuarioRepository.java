package coac.fasay.cobroagua.Repository;

import coac.fasay.cobroagua.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM tbl_usuario usu WHERE usu.usu_user = :user", nativeQuery = true)
    public Usuario findByUsername(String user);

}
