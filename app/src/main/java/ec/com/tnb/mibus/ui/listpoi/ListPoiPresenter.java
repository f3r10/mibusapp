package ec.com.tnb.mibus.ui.listpoi;

import java.util.List;

import javax.inject.Inject;

import ec.com.tnb.mibus.data.DataManager;
import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.ui.base.BasePresenter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by f3r10 on 5/6/16.
 */

public class ListPoiPresenter extends BasePresenter<ListPoiView> {

    private final DataManager mDataManager;
    private Subscription mSusSubscription;

    @Inject
    public ListPoiPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ListPoiView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(mSusSubscription != null) mSusSubscription.unsubscribe();
    }

    public void loadCacheListBusStations(){
        checkViewAttached();
        mSusSubscription = mDataManager.getCacheBusStations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BusStation>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the users");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<BusStation> busStations) {
                        if(busStations.isEmpty()){
                            getMvpView().showBusStationsEmpty();
                        }else {
                            getMvpView().showListBusStationNear(busStations);
                        }
                    }
                });
    }

    public void loadOnlineListBusStations(){
        checkViewAttached();
        mSusSubscription = mDataManager.getOnlineBusStations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BusStation>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the users");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<BusStation> busStations) {
                        if(busStations.isEmpty()){
                            getMvpView().showBusStationsEmpty();
                        }else {
                            getMvpView().showListBusStationNear(busStations);
                        }
                    }
                });
    }
}
