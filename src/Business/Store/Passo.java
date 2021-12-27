package Business.Store;

import java.time.Duration;

public class Passo {
    private float custoPasso;
    private Duration tempoPasso;

    //private long minutes = 2880; 2 dias 

    public Passo() {
        this.custoPasso = 0;
        this.tempoPasso = Duration.ofMinutes(0);
    }
    
    public Passo(float custoPasso, Duration tempoPasso){
        this.custoPasso = custoPasso;
        this.tempoPasso = tempoPasso;
    }

    public Passo(Passo passo){
        this.custoPasso = passo.getCustoPasso();
        this.tempoPasso = passo.getTempoPasso();
    }

    public Duration getTempoPasso(){
        return this.tempoPasso;
    }

    public float getCustoPasso(){
        return this.custoPasso;
    }

    public void setTempoPasso(Duration tempoPasso){
        this.tempoPasso = this.tempoPasso;
    }

    public void setCustoPasso(Float custoPasso){
        this.custoPasso = custoPasso;
    }
/*
    public String toString(){
        DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "Passo{" +
                    "Custo do Passo: " + custoPasso +
                    "Tempo do Passo: " + tempoPasso.format(dataformatada) +
                '}';
        }

        public Passo clone(){
            return new Passo(this);
        }

        public boolean equals(Object obj){
            if (obj == this) return true;
            if (obj == null || obj.getClass().equals(this.getClass())) return false;
            Passo passo = (Passo) obj;
            return this.custoPasso == passo.getCustoPasso() &&
                    this.tempoPasso == passo.getTempoPasso();
        }
 */
    }