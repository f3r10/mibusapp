package ec.com.tnb.mibus.injection.component;

import dagger.Component;
import ec.com.tnb.mibus.injection.scope.PerActivity;
import ec.com.tnb.mibus.injection.module.ActivityModule;
import ec.com.tnb.mibus.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
