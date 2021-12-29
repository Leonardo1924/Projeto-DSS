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

    public void registaEquip(int nif, String idEquip, String estado){
        Equipamento equip = new Equipamento(idEquip, estado);
        this.equipamentos.put(nif, equip.clone());
    }

    public String consultaEstado(int nif){
        return this.equipamentos.get(nif).getEstado();
    }

    public void levantaEquipamento(int nif){
        this.equipamentos.get(nif).setEstado("entregue");
    }

    public boolean apagaEquipamento(int nif){
        if(this.equipamentos.containsKey(nif)) {
            this.equipamentos.remove(nif);
            return true;
        }
        else return false;
    }

    public boolean existeEquipamentoNIF(int nif){
        return this.equipamentos.containsKey(nif);
    }

    public boolean existeEquipamentoID(String id){
        for(Equipamento eq: this.equipamentos.values()){
            if(eq.getId().equals(id)) return true;
        }
        return false;
    }
}
