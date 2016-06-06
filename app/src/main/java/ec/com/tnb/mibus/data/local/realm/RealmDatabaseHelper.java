package ec.com.tnb.mibus.data.local.realm;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import ec.com.tnb.mibus.data.model.busstation.BusStation;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by f3r10 on 5/6/16.
 */

@Singleton
public class RealmDatabaseHelper {

    private final Provider<Realm> mRealmProvider;

    @Inject
    public RealmDatabaseHelper(Provider<Realm> realmProvider) {
        this.mRealmProvider = realmProvider;
    }

    public Observable<BusStation> setBusStations(final Collection<BusStation> newBusStations){
        return Observable.create(new Observable.OnSubscribe<BusStation>() {
            @Override
            public void call(Subscriber<? super BusStation> subscriber) {
                if(subscriber.isUnsubscribed()) return;
                Realm realm = null;

                try{
                    realm = mRealmProvider.get();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(newBusStations);
                    realm.commitTransaction();
                }catch (Exception e){
                    Timber.e(e, "There was an error while adding in Realm");
                    subscriber.onError(e);
                }finally {
                    if(realm != null){
                        realm.close();
                    }
                }
            }
        });
    }

    public Observable<List<BusStation>> getBusStations(){
        final Realm realm = mRealmProvider.get();
        return realm.where(BusStation.class).findAllAsync().asObservable()
                .filter(new Func1<RealmResults<BusStation>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<BusStation> busStations) {
                        return busStations.isLoaded();
                    }
                })
                .map(new Func1<RealmResults<BusStation>, List<BusStation>>() {
                    @Override
                    public List<BusStation> call(RealmResults<BusStation> busStations) {
                        return realm.copyFromRealm(busStations);
                    }
                });
    }
}
