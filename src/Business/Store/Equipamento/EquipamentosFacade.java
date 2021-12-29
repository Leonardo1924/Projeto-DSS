package Business.Store.Equipamento;

import java.util.Map;
import java.util.stream.Collectors;

public class EquipamentosFacade implements IGestEquipamentos {
    private Map<Integer, Equipamento> equipamentos;

    public EquipamentosFacade(Map<Integer,Equipamento> equipamentos){
        this.equipamentos = equipamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public Map<Integer,Equipamento> getEquipamentos() {
        return this.equipamentos.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e-> e.getValue().clone()));
    }

    public boolean registaEquip(int nif, String idEquip, String estado){
        if(!this.equipamentos.containsKey(nif)) {
            Equipamento equip = new Equipamento(idEquip, estado);
            this.equipamentos.put(nif, equip.clone());
            return true;
        }
        else return false;
    }

    public String consultaEstado(int nif){
        return this.equipamentos.get(nif).getEstado();
    }

    public void levantaEquipamento(int nif){
        this.equipamentos.get(nif).setEstado("entregue");
    }

    public void apagaEquipamento(int nif){
        this.equipamentos.remove(nif);
    }
}
