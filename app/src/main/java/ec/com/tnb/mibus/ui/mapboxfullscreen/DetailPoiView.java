package ec.com.tnb.mibus.ui.mapboxfullscreen;

import java.util.List;

import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.ui.base.MvpView;

/**
 * Created by f3r10 on 30/5/16.
 */

public interface DetailPoiView extends MvpView {

    void showBusStationsNear(List<BusStation> stations);
}
