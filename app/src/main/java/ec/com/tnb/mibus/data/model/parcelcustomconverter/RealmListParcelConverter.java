package ec.com.tnb.mibus.data.model.parcelcustomconverter;

import org.parceler.converter.CollectionParcelConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by f3r10 on 5/6/16.
 */

public abstract class RealmListParcelConverter<T extends RealmObject> extends CollectionParcelConverter<T, RealmList<T>> {

    @Override
    public RealmList<T> createCollection(){
        return new RealmList<T>();
    }
}
