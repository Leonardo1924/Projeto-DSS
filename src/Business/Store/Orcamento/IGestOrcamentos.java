package Business.Store.Orcamento;

import java.time.LocalDateTime;
import java.util.Map;

public interface IGestOrcamentos {

    Map<Integer,Orcamento> getOrcamentos();

    void consultaOrcamentos();

    void removeOrcamento(Integer id);

    void registaOrcamento(int idOrc, String idEq, LocalDateTime now, String notas);

    void atualizaOrcamento(Integer idOrc, float custo, int prazo, String idTecnico, String idPlano);
}