package ec.com.tnb.mibus.data.model.parcelcustomconverter;

import android.os.Parcel;

import org.parceler.Parcels;

import ec.com.tnb.mibus.data.model.busstation.Route;

/**
 * Created by f3r10 on 5/6/16.
 */

public class RouteListParcelConverter extends RealmListParcelConverter<Route> {

    @Override
    public void itemToParcel(Route input, Parcel parcel){
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Route itemFromParcel(Parcel parcel){
        return Parcels.unwrap(parcel.readParcelable(Route.class.getClassLoader()));
    }
}
