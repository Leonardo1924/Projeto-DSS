package Business;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SubPasso extends Passo{

    public SubPasso() {
        super();
    }

    public SubPasso(float custoPasso, LocalTime tempoPasso){
        super(custoPasso,tempoPasso);
    }

    public SubPasso(SubPasso subPasso){
        super(subPasso);
    }
/*
    public SubPasso clone(){
        return new SubPasso(this);
    }
 */
    }