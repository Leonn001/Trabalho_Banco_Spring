package leon.ucsal.trabalhobanco.repository;

import leon.ucsal.trabalhobanco.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE s.tipoServico.id = :idTipo")
    List<Servico> findByTipoServicoId(@Param("idTipo") Long idTipo);

    @Query("SELECT s FROM Servico s WHERE s.usuario.id = :idUsuario")
    List<Servico> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}
