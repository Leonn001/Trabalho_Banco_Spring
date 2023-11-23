package leon.ucsal.trabalhobanco.requests;

import leon.ucsal.trabalhobanco.domain.TipoServico;
import leon.ucsal.trabalhobanco.domain.Usuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicoPostRequestBody {
    private String descricao;
    private Usuario usuario;
    private TipoServico tipoServico;
}