package leon.ucsal.trabalhobanco.requests;

import lombok.Builder;
import lombok.Data;

@Data
public class TipoServicoPostRequestBody {
    private String tipo;
    public TipoServicoPostRequestBody() {
        //
    }
}
