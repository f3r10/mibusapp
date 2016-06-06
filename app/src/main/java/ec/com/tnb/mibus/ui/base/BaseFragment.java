package ec.com.tnb.mibus.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ec.com.tnb.mibus.BoilerplateApplication;
import ec.com.tnb.mibus.injection.component.DaggerFragmentComponent;
import ec.com.tnb.mibus.injection.component.FragmentComponent;
import ec.com.tnb.mibus.injection.module.FragmentModule;

/**
 * Created by f3r10 on 30/5/16.
 */

public class BaseFragment extends Fragment {

    private FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public FragmentComponent getFragmentComponent(){
        if(mFragmentComponent == null){
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(BoilerplateApplication.get(getActivity()).getComponent())
                    .build();
        }

        return mFragmentComponent;
    }




}
