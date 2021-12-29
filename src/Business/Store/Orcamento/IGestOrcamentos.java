package Business.Store.Orcamento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public interface IGestOrcamentos {

    Map<Integer,Orcamento> getOrcamentos();

    void consultaOrcamentos();

    boolean removeOrcamento(Integer id);

    void registaOrcamento(int idOrc, String idEq, LocalDateTime now, String notas);

    void atualizaOrcamento(int idOrc, String idTecnico, int idPlano, float custoTotal, Duration prazoTotal);

    boolean existeOrcamento(int id);
}