package Business.Store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Business.PlanoTrabalho;
import Business.Store.Funcionario.Funcionario;


public class Orcamento {
    private Equipamento equipamento;              //equipamento
    private LocalDateTime data;
    private float custo;
    private int prazo;  
    private Funcionario funcionario;
    private String notas;
    private PlanoTrabalho plano;

    public Orcamento(){
        this.equipamento = new Equipamento();
        this.data = LocalDateTime.now();
        this.custo = 0;
        this.prazo = 0;
        this.funcionario = new Funcionario();
        this.notas = "";
        this.plano = new PlanoTrabalho();
    }

    public Orcamento(Equipamento equipamento, LocalDateTime data, float custo, int prazo, Funcionario funcionario, String notas, PlanoTrabalho plano){
        this.equipamento = equipamento;
        this.data = LocalDateTime.now();
        this.custo = custo;
        this.prazo = prazo;
        this.funcionario = funcionario;
        this.notas = notas;
        this.plano = plano;
    }

    public Orcamento(Orcamento orcamento){
        this.equipamento = orcamento.getEquipamento();
        this.data = orcamento.getData();
        this.custo = orcamento.getCusto();
        this.prazo = orcamento.getPrazo();
        this.funcionario = orcamento.getFuncionario();
        this.notas = orcamento.getNotas();
        this.plano = orcamento.getPlano();
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public Equipamento getEquipamento(){
        return this.equipamento;
    }

    public float getCusto(){
        return this.custo;
    }

    public int getPrazo(){
        return this.prazo;
    }

    public Funcionario getFuncionario(){
        return this.funcionario;
    }

    public String getNotas(){
        return this.notas;
    }

    public PlanoTrabalho getPlano(){
        return this.plano;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public void setEquipamento(Equipamento equipamento){
        this.equipamento = equipamento;
    }

    public void setCusto(float custo){
        this.custo = custo;
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }

    public void setNotas(String notas){
        this.notas = notas;
    }

    public void setPlano(PlanoTrabalho plano){
        this.plano = plano;
    }

    /*public void calculaPrazo(){
        this.prazo = this.plano.tempoTotalPlano();
    }*/
/*
    public void calcularCusto(){  
        String somaTempo = this.plano.tempoTotalPlano().toString();
        float somaCusto = this.plano.custoTotalPlano();
        this.custo = Float.parseFloat(somaTempo)*0.25 + somaCusto;
    }
*/
    public String toString(){
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "Orcamento{" + 
                "Equipamento: " + equipamento.toString() +
                "Data: " + data.format(dataformatada) +
                "Custo: " + custo +
                "Prazo: " + prazo +
                "Funcionario:" + funcionario.toString() + 
                "Notas:" + notas + 
                "Plano: " + plano.toString() +
                '}';
    }

    public Orcamento clone(){
        return new Orcamento(this);
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Orcamento orcamento = (Orcamento) obj;
        return this.equipamento.equals(orcamento.getEquipamento()) && 
                this.data == orcamento.getData() &&
                this.custo == orcamento.getCusto() &&
                this.prazo == orcamento.getPrazo() &&
                this.funcionario.equals(orcamento.getFuncionario()) &&
                this.notas.equals(orcamento.getNotas()) &&
                this.plano.equals(orcamento.getPlano());
    }
}
