package leon.ucsal.trabalhobanco.service;

import leon.ucsal.trabalhobanco.model.TipoServico;
import leon.ucsal.trabalhobanco.repository.TipoServicoRepository;
import leon.ucsal.trabalhobanco.requests.TipoServicoPostRequestBody;
import leon.ucsal.trabalhobanco.requests.TipoServicoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoServicoService {

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    public List<TipoServico> tipoServicoList() {
        return tipoServicoRepository.findAll();
    }

    public TipoServico findByIdOrThrowBadRequestException(long id) {
        return tipoServicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo servico not found"));
    }

    public TipoServico save(TipoServicoPostRequestBody tipoServicoPostRequestBody){
        TipoServico tipoServico = TipoServico.builder().tipo(tipoServicoPostRequestBody.getTipo()).build();
        return tipoServicoRepository.save(tipoServico);
    }

    public void delete(long id){
        tipoServicoRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TipoServicoPutRequestBody tipoServicoPutRequestBody) {
        TipoServico tipoServicoSaved = findByIdOrThrowBadRequestException(tipoServicoPutRequestBody.getId());
        TipoServico tipoServico = TipoServico.builder()
                .id(tipoServicoPutRequestBody.getId())
                .tipo(tipoServicoPutRequestBody.getTipo())
                .build();
        tipoServicoRepository.save(tipoServico);
    }
}
