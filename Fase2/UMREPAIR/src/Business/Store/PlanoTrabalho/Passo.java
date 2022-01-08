package Business.Store.PlanoTrabalho;

import java.time.Duration;
import java.util.Objects;

public class Passo {
    private String descricao;
    private float custoPasso;
    private Duration tempoPasso;

    public Passo(String descricao, float custoPasso, Duration tempoPasso){
        this.descricao = descricao;
        this.custoPasso = custoPasso;
        this.tempoPasso = tempoPasso;
    }

    public Passo(Passo passo){
        this.descricao = passo.getDescricao();
        this.custoPasso = passo.getCustoPasso();
        this.tempoPasso = passo.getTempoPasso();
    }

    public Duration getTempoPasso(){
        return this.tempoPasso;
    }

    public float getCustoPasso(){
        return this.custoPasso;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passo passo = (Passo) o;
        return Float.compare(passo.custoPasso, custoPasso) == 0 && Objects.equals(descricao, passo.descricao) && Objects.equals(tempoPasso, passo.tempoPasso);
    }

    public Passo clone(){
        return new Passo(this);
    }

    public String toString(){
        return "\033[1;35mDescriçao: \033[0m" + this.descricao +
                " \033[1;35mCusto: \033[0m" + this.custoPasso +
                " \033[1;35mTempo: \033[0m" + this.tempoPasso;
    }
}