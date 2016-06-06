package ec.com.tnb.mibus.data.model.parcelcustomconverter;

import android.os.Parcel;

import org.parceler.Parcels;

import ec.com.tnb.mibus.data.model.busstation.RealmDouble;

/**
 * Created by f3r10 on 5/6/16.
 */

public class DoubleListParcelConverter extends RealmListParcelConverter<RealmDouble> {

    @Override
    public void itemToParcel(RealmDouble input, Parcel parcel){
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public RealmDouble itemFromParcel(Parcel parcel){
        return Parcels.unwrap(parcel.readParcelable(RealmDouble.class.getClassLoader()));
    }
}
