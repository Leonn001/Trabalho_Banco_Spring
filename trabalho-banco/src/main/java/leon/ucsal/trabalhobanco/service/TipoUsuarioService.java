package leon.ucsal.trabalhobanco.service;

import leon.ucsal.trabalhobanco.domain.TipoServico;
import leon.ucsal.trabalhobanco.domain.TipoUsuario;
import leon.ucsal.trabalhobanco.repository.TipoUsuarioRepository;
import leon.ucsal.trabalhobanco.requests.TipoServicoPostRequestBody;
import leon.ucsal.trabalhobanco.requests.TipoUsuarioPostRequestBody;
import leon.ucsal.trabalhobanco.requests.TipoUsuarioPutRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public List<TipoUsuario> tipoUsuarioList(){
        return tipoUsuarioRepository.findAll();
    }

    public TipoUsuario findByIdOrThrowBadRequestException(long id){
        return tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo usuario not found"));
    }

    public TipoUsuario save(TipoUsuarioPostRequestBody tipoUsuarioPostRequestBody){
        TipoUsuario tipoUsuario = TipoUsuario.builder().tipo(tipoUsuarioPostRequestBody.getTipo()).build();
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    public void delete(long id){
        tipoUsuarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TipoUsuarioPutRequestBody tipoUsuarioPutRequestBody){
        TipoUsuario tipoUsuarioSaved = findByIdOrThrowBadRequestException(tipoUsuarioPutRequestBody.getId());
        TipoUsuario tipoUsuario = TipoUsuario.builder()
                .id(tipoUsuarioPutRequestBody.getId())
                .tipo(tipoUsuarioPutRequestBody.getTipo())
                .build();
        tipoUsuarioRepository.save(tipoUsuario);
    }
}
