package ec.com.tnb.mibus.ui.listpoi;

import java.util.List;

import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.ui.base.MvpView;

/**
 * Created by f3r10 on 5/6/16.
 */

public interface ListPoiView extends MvpView {

    void showListBusStationNear(List<BusStation> stationList);
    void showError();
    void showBusStationsEmpty();
}
