package Business.Store.Equipamento;

import java.util.Map;

public interface IGestEquipamentos {
    Map<Integer, Equipamento> getEquipamentos();

    boolean registaEquip(int nif, String idEquip, String estado);

    String consultaEstado(int nif);

    void levantaEquipamento(int nif);

    void apagaEquipamento(int nif);

    boolean existeEquipamento(int nif);
}
