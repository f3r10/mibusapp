package ec.com.tnb.mibus.data.remote;

import java.util.List;

import ec.com.tnb.mibus.data.model.busstation.BusStation;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by f3r10 on 5/6/16.
 */

public interface RestMiBusService {

    @GET("parada_cercanas/-0.13709799945354462/-78.49885559082031/100")
    Observable<List<BusStation>> getBusStations();
}
