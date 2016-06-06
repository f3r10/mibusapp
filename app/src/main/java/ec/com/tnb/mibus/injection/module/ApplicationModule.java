package ec.com.tnb.mibus.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ec.com.tnb.mibus.data.remote.MiBusService;
import ec.com.tnb.mibus.data.remote.RibotsService;
import ec.com.tnb.mibus.injection.context.ApplicationContext;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    //Options of services with retrofit

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    MiBusService provideMiBusService(){
        return MiBusService.Creator.newMiBusService();
    }

    @Provides
    @Singleton
    RealmConfiguration provideRealConfiguration(@ApplicationContext Context context){
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder(context);
        builder.name("mibus.realm");
        return builder.build();
    }

    @Provides
    Realm provideRealm(RealmConfiguration realmConfiguration){
        return Realm.getInstance(realmConfiguration);
    }

}
