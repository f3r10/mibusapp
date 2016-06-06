package ec.com.tnb.mibus.data.model.busstation;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;


import ec.com.tnb.mibus.data.model.parcelcustomconverter.RouteListParcelConverter;
import io.realm.BusStationRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by f3r10 on 29/5/16.
 */

@Parcel(implementations = BusStationRealmProxy.class,
        value = Parcel.Serialization.BEAN,
        analyze = {BusStation.class})
public class BusStation extends RealmObject {
    private Integer v;
    private String id;
    private Boolean isDraft;
    private String nameStation;
    private String route;
    private RealmList<Route> routes;
    private String stopUuid;
    private String updatedAt;
    private Loc loc;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getRoute() {
        return route;
    }


    public void setRoute(String route) {
        this.route = route;
    }

    public RealmList<Route> getRoutes() {
        return routes;
    }

    @ParcelPropertyConverter(RouteListParcelConverter.class)
    public void setRoutes(RealmList<Route> routes) {
        this.routes = routes;
    }

    public String getStopUuid() {
        return stopUuid;
    }

    public void setStopUuid(String stopUuid) {
        this.stopUuid = stopUuid;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Loc getLoc() {
        return loc;
    }

    public void setLoc(Loc loc) {
        this.loc = loc;
    }
}
