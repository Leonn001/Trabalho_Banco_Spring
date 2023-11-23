package leon.ucsal.trabalhobanco.repository;

import leon.ucsal.trabalhobanco.domain.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

}
