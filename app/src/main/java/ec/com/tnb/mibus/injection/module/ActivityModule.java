package ec.com.tnb.mibus.injection.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ec.com.tnb.mibus.injection.context.ActivityContext;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
