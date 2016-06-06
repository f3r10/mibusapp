package ec.com.tnb.mibus.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import ec.com.tnb.mibus.data.DataManager;
import ec.com.tnb.mibus.data.SyncService;
import ec.com.tnb.mibus.data.local.sqlite.DatabaseHelper;
import ec.com.tnb.mibus.data.local.PreferencesHelper;
import ec.com.tnb.mibus.data.remote.MiBusService;
import ec.com.tnb.mibus.data.remote.RestMiBusService;
import ec.com.tnb.mibus.data.remote.RibotsService;
import ec.com.tnb.mibus.injection.context.ApplicationContext;
import ec.com.tnb.mibus.injection.module.ApplicationModule;
import ec.com.tnb.mibus.injection.module.RestMiBusModule;
import io.realm.Realm;

@Singleton
@Component(modules = {ApplicationModule.class, RestMiBusModule.class})
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    MiBusService miBusServices();
    RestMiBusService restMiBusService();
    Realm realm();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
