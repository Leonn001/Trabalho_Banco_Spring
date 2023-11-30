package leon.ucsal.trabalhobanco.controller;

import leon.ucsal.trabalhobanco.model.Servico;
import leon.ucsal.trabalhobanco.requests.ServicoPostRequestBody;
import leon.ucsal.trabalhobanco.requests.ServicoPutRequestBody;
import leon.ucsal.trabalhobanco.service.ServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servicos")
@Log4j2
@RequiredArgsConstructor
public class ServicoController {
    @Autowired
    private ServicoService servicosService;

    @GetMapping
    public ResponseEntity<List<Servico>> list(){
        return new ResponseEntity<>(servicosService.servicoList(), HttpStatus.OK);
    }

    @GetMapping(path = "/byTipo/{idTipo}")
    public ResponseEntity<List<Servico>> listByTipo(@PathVariable Long idTipo) {
        return servicosService.listByTipo(idTipo);
    }

    @GetMapping(path = "/byUsuario/{idUsuario}")
    public ResponseEntity<List<Servico>> listByUsuario(@PathVariable Long idUsuario) {
        return servicosService.listByUsuarioId(idUsuario);
    }

    @GetMapping(path = "byId/{id}")
    public ResponseEntity<Servico> findById(@PathVariable long id){
        return new ResponseEntity<>(servicosService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Servico> save(@RequestBody ServicoPostRequestBody servicoPostRequestBody){
        Long tipoServicoId = servicoPostRequestBody.getTipoServico().getId();
        Long usuarioId = servicoPostRequestBody.getUsuario().getId();
        return new ResponseEntity<>(servicosService.save(usuarioId,tipoServicoId,servicoPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        servicosService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ServicoPutRequestBody servicoPutRequestBody){
        servicosService.replace(servicoPutRequestBody);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
