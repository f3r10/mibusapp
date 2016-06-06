package ec.com.tnb.mibus.ui.mapboxfullscreen;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import ec.com.tnb.mibus.data.DataManager;
import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.ui.base.BasePresenter;

/**
 * Created by f3r10 on 30/5/16.
 */

public class DetailPoiPresenter extends BasePresenter<DetailPoiView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public DetailPoiPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(DetailPoiView detailPoiView) {
        super.attachView(detailPoiView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadStations() {
        checkViewAttached();
        mSubscription = mDataManager.getStations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<BusStation>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");

                    }

                    @Override
                    public void onNext(List<BusStation> busStations) {
                        if (busStations.isEmpty()) {
                            Timber.d("No hay nada jejeje");
                        } else {
                            getMvpView().showBusStationsNear(busStations);
                        }
                    }
                });
    }
}
