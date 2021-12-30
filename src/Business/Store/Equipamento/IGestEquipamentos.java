package Business.Store.Equipamento;

import java.util.Map;

public interface IGestEquipamentos {
    Map<Integer, Equipamento> getEquipamentos();

    void registaEquip(int nif, String idEquip, String estado);

    String consultaEstado(int nif);

    void levantaEquipamento(int nif);

    boolean apagaEquipamento(int nif);

    boolean existeEquipamentoNIF(int nif);

    boolean existeEquipamentoID(String id);

    void equipEstado(String id, String estado);

    void atualizaEstado(int eqID, String estado);
}
