package ec.com.tnb.mibus.data.model.busstation;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.RouteRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by f3r10 on 29/5/16.
 */

@Parcel(implementations = {RouteRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Route.class})
public class Route extends RealmObject {

    @PrimaryKey
    public String routeUuid;
    public String originRouteName;
    public String finalRouteName;
    public String busCompany;
    public String id;

    public String getRouteUuid() {
        return routeUuid;
    }

    public void setRouteUuid(String routeUuid) {
        this.routeUuid = routeUuid;
    }

    public String getOriginRouteName() {
        return originRouteName;
    }

    public void setOriginRouteName(String originRouteName) {
        this.originRouteName = originRouteName;
    }

    public String getFinalRouteName() {
        return finalRouteName;
    }

    public void setFinalRouteName(String finalRouteName) {
        this.finalRouteName = finalRouteName;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
