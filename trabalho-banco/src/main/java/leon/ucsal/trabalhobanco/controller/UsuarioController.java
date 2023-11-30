package leon.ucsal.trabalhobanco.controller;

import leon.ucsal.trabalhobanco.model.Usuario;
import leon.ucsal.trabalhobanco.requests.UsuarioPostRequestBody;
import leon.ucsal.trabalhobanco.requests.UsuarioPutRequestBody;
import leon.ucsal.trabalhobanco.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> list(){
        return new ResponseEntity<>(usuarioService.usuarioList(), HttpStatus.OK);
    }

    @GetMapping(path = "/byTipo/{idTipo}")
    public ResponseEntity<List<Usuario>> listByTipo(@PathVariable long id){
        return usuarioService.listByTipo(id);
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id){
        return new ResponseEntity<>(usuarioService.findByIdOrThrowBadRequestException(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody){
        Long tipoUsuarioId = usuarioPostRequestBody.getTipoUsuario().getId();
        return new ResponseEntity<>(usuarioService.save(tipoUsuarioId,usuarioPostRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        usuarioService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UsuarioPutRequestBody usuarioPutRequestBody){
        usuarioService.replace(usuarioPutRequestBody);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
