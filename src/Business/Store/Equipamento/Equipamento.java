package Business.Store.Equipamento;

import java.io.PrintWriter;

public class Equipamento {
    private String id;
    private String estado; // em processamento ; reparado ; entregue

    public Equipamento(){
        this.id = "";
        this.estado = "";
    }

    public Equipamento(String Id,String estado){
        this.id = Id;
        this.estado = estado;
    }

    public Equipamento(Equipamento equipamento){
        this.id = equipamento.getId();
        this.estado = equipamento.getEstado();
    }

    public String getId(){
        return this.id;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public Equipamento clone(){
        return new Equipamento(this);
    }

    public void gravar(int nif, PrintWriter print){
        print.println(nif + ";" + this.id + ";" + this.estado);
    }

    public String toString(){
        return "\033[1;35mID: \033[0m" + id +
                " \033[1;35mEstado: \033[0m" + estado;
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Equipamento equipamento = (Equipamento) obj;
        return this.id.equals(equipamento.getId()) &&
                this.estado.equals(equipamento.getEstado());
    }
}
