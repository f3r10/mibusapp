package ec.com.tnb.mibus.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ec.com.tnb.mibus.data.local.realm.RealmDatabaseHelper;
import ec.com.tnb.mibus.data.remote.RestMiBusService;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import ec.com.tnb.mibus.data.local.sqlite.DatabaseHelper;
import ec.com.tnb.mibus.data.local.PreferencesHelper;
import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.data.model.Ribot;
import ec.com.tnb.mibus.data.remote.MiBusService;
import ec.com.tnb.mibus.data.remote.RibotsService;
import ec.com.tnb.mibus.util.EventPosterHelper;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final MiBusService mMiBusService;
    private final RestMiBusService mRestMiBusService;

    private final DatabaseHelper mDatabaseHelper;
    private final RealmDatabaseHelper mRealmDatabaseHelper;

    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(RibotsService ribotsService, RestMiBusService restMiBusService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper,
                       MiBusService miBusService, RealmDatabaseHelper realmDatabaseHelper) {
        mRibotsService = ribotsService;
        mRestMiBusService = restMiBusService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
        mMiBusService = miBusService;
        mRealmDatabaseHelper = realmDatabaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    /*
    Start Realm custom service
     */

    public Observable<BusStation> syncBusStations(){
        return mRestMiBusService.getBusStations()
                .concatMap(new Func1<List<BusStation>, Observable<? extends BusStation>>() {
                    @Override
                    public Observable<BusStation> call(List<BusStation> busStations) {
                        return mRealmDatabaseHelper.setBusStations(busStations);
                    }
                });
    }

    public Observable<List<BusStation>> getCacheBusStations(){
        return mRealmDatabaseHelper.getBusStations().distinct();
    }

    public Observable<List<BusStation>> getOnlineBusStations(){
        return mRestMiBusService.getBusStations().distinct();
    }

    /*
    End Realm custom service
     */



    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mRibotsService.getRibots().distinct();
    }

    public Observable<List<BusStation>> getStations(){
        return mMiBusService.getStations().distinct();
    }


    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }

}
