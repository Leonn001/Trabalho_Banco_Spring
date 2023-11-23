package leon.ucsal.trabalhobanco.controller;

import leon.ucsal.trabalhobanco.domain.TipoUsuario;
import leon.ucsal.trabalhobanco.repository.TipoUsuarioRepository;
import leon.ucsal.trabalhobanco.requests.TipoUsuarioPostRequestBody;
import leon.ucsal.trabalhobanco.requests.TipoUsuarioPutRequestBody;
import leon.ucsal.trabalhobanco.service.TipoUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tipoUsuarios")
@Log4j2
@RequiredArgsConstructor
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> list(){
        return new ResponseEntity<>(tipoUsuarioService.tipoUsuarioList(), HttpStatus.OK);
    }

    @GetMapping(path = "/id{id}")
    public ResponseEntity<TipoUsuario> findById(@PathVariable long id){
        return new ResponseEntity<>(tipoUsuarioService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> save(@RequestBody TipoUsuarioPostRequestBody tipoUsuarioPostRequestBody) {
        return new ResponseEntity<>(tipoUsuarioService.save(tipoUsuarioPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        tipoUsuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TipoUsuarioPutRequestBody tipoUsuarioPutRequestBody){
        tipoUsuarioService.replace(tipoUsuarioPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
