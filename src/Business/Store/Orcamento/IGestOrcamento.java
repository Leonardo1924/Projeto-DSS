package Business.Store.Orcamento;

import java.time.LocalDateTime;
import java.util.Map;

public interface IGestOrcamento {

    Map<Integer,Orcamento> getOrcamentos();

    void registaOrcamento(int id,String idEq, LocalDateTime data, float custo, int prazo, String tecnico, String notas, String idPlano);

    void consultaOrcamentos();
}