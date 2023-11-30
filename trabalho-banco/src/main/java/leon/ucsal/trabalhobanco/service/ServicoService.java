package leon.ucsal.trabalhobanco.service;

import leon.ucsal.trabalhobanco.model.Servico;
import leon.ucsal.trabalhobanco.model.TipoServico;
import leon.ucsal.trabalhobanco.model.Usuario;
import leon.ucsal.trabalhobanco.repository.ServicoRepository;
import leon.ucsal.trabalhobanco.repository.TipoServicoRepository;
import leon.ucsal.trabalhobanco.repository.UsuarioRepository;
import leon.ucsal.trabalhobanco.requests.ServicoPostRequestBody;
import leon.ucsal.trabalhobanco.requests.ServicoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Servico> servicoList() {
        return servicoRepository.findAll();
    }

    public Servico findByIdOrThrowBadRequestException(long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servico not found"));
    }

    public ResponseEntity<List<Servico>> listByTipo(Long idTipo) {
        List<Servico> servicos = servicoRepository.findByTipoServicoId(idTipo);

        if (servicos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servicos);
    }

    public ResponseEntity<List<Servico>> listByUsuarioId(Long idUsuario) {
        List<Servico> servicos = servicoRepository.findByUsuarioId(idUsuario);

        if (servicos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servicos);
    }


    public Servico save(Long usuarioId,Long tipoServicoId, ServicoPostRequestBody servicoPostRequestBody) {
        TipoServico tipoServico = tipoServicoRepository.findById(tipoServicoId)
                .orElseThrow(() -> new IllegalArgumentException("TipoServico não encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        Servico servico = Servico.builder()
                .descricao(servicoPostRequestBody.getDescricao())
                .usuario(usuario)
                .tipoServico(tipoServico)
                .build();

        return servicoRepository.save(servico);
    }

    public void delete(long id) {
        Servico servicoDelete = findByIdOrThrowBadRequestException(id);
        servicoDelete.setTipoServico(null);
        servicoDelete.setUsuario(null);
        servicoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ServicoPutRequestBody servicoPutRequestBody) {
        Servico servicoSaved = findByIdOrThrowBadRequestException(servicoPutRequestBody.getId());
        Servico servico = Servico.builder()
                .id(servicoSaved.getId())
                .descricao(servicoPutRequestBody.getDescricao())
                .usuario(servicoPutRequestBody.getUsuario())
                .tipoServico(servicoPutRequestBody.getTipoServico())
                .build();

        servicoRepository.save(servico);
    }
}
