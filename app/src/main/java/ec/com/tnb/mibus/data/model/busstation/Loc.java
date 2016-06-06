package ec.com.tnb.mibus.data.model.busstation;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;


import ec.com.tnb.mibus.data.model.parcelcustomconverter.DoubleListParcelConverter;
import io.realm.LocRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by f3r10 on 29/5/16.
 */

@Parcel(implementations = LocRealmProxy.class,
        value = Parcel.Serialization.BEAN,
        analyze = {Loc.class})
public class Loc extends RealmObject{
    private String type;
    private RealmList<RealmDouble> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RealmList<RealmDouble> getCoordinates() {
        return coordinates;
    }

    @ParcelPropertyConverter(DoubleListParcelConverter.class)
    public void setCoordinates(RealmList<RealmDouble> coordinates) {
        this.coordinates = coordinates;
    }
}
