package leon.ucsal.trabalhobanco.repository;

import leon.ucsal.trabalhobanco.model.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {

}
