package leon.ucsal.trabalhobanco.controller;

import leon.ucsal.trabalhobanco.model.TipoServico;
import leon.ucsal.trabalhobanco.repository.TipoServicoRepository;
import leon.ucsal.trabalhobanco.requests.TipoServicoPostRequestBody;
import leon.ucsal.trabalhobanco.requests.TipoServicoPutRequestBody;
import leon.ucsal.trabalhobanco.service.TipoServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tipoServicos")
@Log4j2
@RequiredArgsConstructor
public class TipoServicoController {

    @Autowired
    private TipoServicoService tipoServiceService;
    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @GetMapping
    public ResponseEntity<List<TipoServico>> list(){
        return new ResponseEntity<>(tipoServiceService.tipoServicoList(), HttpStatus.OK);
    }

    @GetMapping(path = "byId/{id}")
    public ResponseEntity<TipoServico> findById(@PathVariable long id) {
        return new ResponseEntity<>(tipoServiceService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipoServico> save(@RequestBody TipoServicoPostRequestBody tipoServicoPostRequestBody) {
        return new ResponseEntity<>(tipoServiceService.save(tipoServicoPostRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        tipoServiceService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TipoServicoPutRequestBody tipoServicoPutRequestBody){
        tipoServiceService.replace(tipoServicoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
