package leon.ucsal.trabalhobanco.repository;

import leon.ucsal.trabalhobanco.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.tipoUsuario.id = :idTipo")
    List<Usuario> findByTipoUsuarioId(@Param("idTipo") Long idTipo);
}
