package ec.com.tnb.mibus.data.remote;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import ec.com.tnb.mibus.data.model.busstation.BusStation;

/**
 * Created by f3r10 on 29/5/16.
 */

public interface MiBusService {

    String ENDPOINT = "http://www.mibusuio.com/api/";
    @GET("parada_cercanas/-0.13709799945354462/-78.49885559082031/100")
    Observable<List<BusStation>> getStations();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static MiBusService newMiBusService() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MiBusService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(MiBusService.class);
        }
    }

}
