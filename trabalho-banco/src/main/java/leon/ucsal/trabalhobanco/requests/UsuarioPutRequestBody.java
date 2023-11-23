package leon.ucsal.trabalhobanco.requests;

import leon.ucsal.trabalhobanco.domain.TipoUsuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioPutRequestBody {
    private Long id;
    private String nome;
    private TipoUsuario tipoUsuario;
    private String contato;
}
