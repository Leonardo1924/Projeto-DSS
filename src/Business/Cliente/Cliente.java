package Business.Cliente;

import Business.Store.Equipamento;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String idCliente;
    private String name;
    private int nif;
    private int telemovel;
    private String mail;
    private Equipamento equipamento;

    public Cliente(){
        this.idCliente = "n/a";
        this.name = "n/a";
        this.nif = 0;
        this.telemovel = 0;
        this.mail = "n/a";
        this.equipamento = new Equipamento();
    }

    public Cliente(String id,String name, int nif, int telemovel, String mail, Equipamento equipamento){
        this.idCliente = id;
        this.name = name;
        this.nif = nif;
        this.telemovel = telemovel;
        this.mail = mail;
        this.equipamento = new Equipamento(equipamento);
    }

    public Cliente(Cliente c){
        this.idCliente = c.getIdCliente();
        this.name = c.getName();
        this.nif = c.getNif();
        this.telemovel = c.getTelemovel();
        this.mail = c.getMail();
        this.equipamento = c.getEquipamento();
    }

    public String getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNif() {
        return this.nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getTelemovel() {
        return this.telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Equipamento getEquipamento(){
        return this.equipamento.clone();
    }

    public void setEquipamento(Equipamento equipamento){
        this.equipamento = new Equipamento(equipamento);
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    public String toString(){
        return "Cliente{" +
                "Id: " + idCliente +
                "Name: " + name +
                "NIF: " + nif +
                "Telemóvel: " + telemovel +
                "Mail: " + mail +
                "Equipamento: " + equipamento.toString() +
                '}';
    }

    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || obj.getClass().equals(this.getClass())) return false;
        Cliente client = (Cliente) obj;
        return this.idCliente == client.getIdCliente() &&
                this.name.equals(client.getName()) &&
                this.nif == client.getNif() &&
                this.telemovel == client.getTelemovel() &&
                this.mail.equals(client.getMail()) &&
                this.equipamento.equals(client.getEquipamento());
    }
}
