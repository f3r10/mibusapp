package ec.com.tnb.mibus.ui;

import android.os.Bundle;

import ec.com.tnb.mibus.R;
import ec.com.tnb.mibus.ui.base.BaseActivity;

public class InitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        initViews();
    }
}
