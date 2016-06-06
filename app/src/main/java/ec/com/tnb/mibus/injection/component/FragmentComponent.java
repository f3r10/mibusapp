package ec.com.tnb.mibus.injection.component;

import dagger.Component;
import ec.com.tnb.mibus.injection.scope.PerFragment;
import ec.com.tnb.mibus.injection.module.FragmentModule;
import ec.com.tnb.mibus.ui.listpoi.ListPoiFragment;
import ec.com.tnb.mibus.ui.mapboxfullscreen.DetailPoiFragment;

/**
 * Created by f3r10 on 30/5/16.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(DetailPoiFragment detailPoiFragment);
    void inject(ListPoiFragment listPoiFragment);
}
