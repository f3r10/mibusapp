package ec.com.tnb.mibus.data.model.busstation;

import io.realm.RealmObject;

/**
 * Created by f3r10 on 5/6/16.
 */

public class RealmDouble extends RealmObject {
    public  Double number;


    public RealmDouble(){

    }

    public RealmDouble(Double number) {
        this.number = number;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }


}
