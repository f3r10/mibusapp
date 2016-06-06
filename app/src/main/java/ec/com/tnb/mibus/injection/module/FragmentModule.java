package ec.com.tnb.mibus.injection.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import ec.com.tnb.mibus.injection.context.FragmentContext;

/**
 * Created by f3r10 on 30/5/16.
 */

@Module
public class FragmentModule {

    private Fragment mFragment;


    public FragmentModule(Fragment fragment){
        mFragment = fragment;
    }

    @Provides
    Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @FragmentContext
    Context provideContext(){
        return mFragment.getActivity();
    }
}
