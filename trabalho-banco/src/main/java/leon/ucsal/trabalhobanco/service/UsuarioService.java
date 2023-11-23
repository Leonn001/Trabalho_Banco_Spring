package leon.ucsal.trabalhobanco.service;

import leon.ucsal.trabalhobanco.domain.Servico;
import leon.ucsal.trabalhobanco.domain.TipoServico;
import leon.ucsal.trabalhobanco.domain.TipoUsuario;
import leon.ucsal.trabalhobanco.domain.Usuario;
import leon.ucsal.trabalhobanco.repository.TipoUsuarioRepository;
import leon.ucsal.trabalhobanco.repository.UsuarioRepository;
import leon.ucsal.trabalhobanco.requests.ServicoPostRequestBody;
import leon.ucsal.trabalhobanco.requests.ServicoPutRequestBody;
import leon.ucsal.trabalhobanco.requests.UsuarioPostRequestBody;
import leon.ucsal.trabalhobanco.requests.UsuarioPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<Usuario> usuarioList() {
        return usuarioRepository.findAll();
    }

    public Usuario findByIdOrThrowBadRequestException(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario not found"));
    }

    public ResponseEntity<List<Usuario>> listByTipo(Long idTipo) {
        List<Usuario> usuarios = usuarioRepository.findByTipoUsuarioId(idTipo);

        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    public Usuario save(Long tipoUsuarioId, UsuarioPostRequestBody usuarioPostRequestBody) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tipoUsuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        Usuario usuario = Usuario.builder()
                .nome(usuarioPostRequestBody.getNome())
                .tipoUsuario(usuarioPostRequestBody.getTipoUsuario())
                .contato(usuarioPostRequestBody.getContato())
                .build();

        return usuarioRepository.save(usuario);
    }

    public void delete(long id) {
        usuarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(UsuarioPutRequestBody usuarioPutRequestBody) {
        Usuario usuarioSaved = findByIdOrThrowBadRequestException(usuarioPutRequestBody.getId());
        Usuario usuario = Usuario.builder()
                .id(usuarioSaved.getId())
                .nome(usuarioPutRequestBody.getNome())
                .tipoUsuario(usuarioPutRequestBody.getTipoUsuario())
                .contato(usuarioPutRequestBody.getContato())
                .build();

        usuarioRepository.save(usuario);
    }
}
