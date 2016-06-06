package ec.com.tnb.mibus.ui.base;

import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ec.com.tnb.mibus.BoilerplateApplication;
import ec.com.tnb.mibus.R;
import ec.com.tnb.mibus.injection.component.ActivityComponent;
import ec.com.tnb.mibus.injection.component.DaggerActivityComponent;
import ec.com.tnb.mibus.injection.module.ActivityModule;
import ec.com.tnb.mibus.ui.listpoi.ListPoiFragment;
import ec.com.tnb.mibus.ui.mapboxfullscreen.DetailPoiFragment;


import static ec.com.tnb.mibus.util.ColorUtil.getNavIconColorState;
import static ec.com.tnb.mibus.util.ColorUtil.getNavTextColorState;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavHandlerListener {

    private ActivityComponent mActivityComponent;

    private NavigationView navigationView = null;
    private NavigationMenuView navigationMenuView = null;
    private DrawerLayout drawer = null;
    private View headerView;
    private RelativeLayout navHeaderImgContainer;
    private RelativeLayout navActionPhone;
    private RelativeLayout navActionMail;
    private RelativeLayout navActionWeb;
    public NavHandlerListener navHandlerListener = null;
    private boolean isDoubleBackToExit = false;

    protected final void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navigationView.setNavigationItemSelectedListener(this);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        navigationView.setItemTextColor(getNavTextColorState());
        navigationView.setItemIconTintList(getNavIconColorState());
        headerView = navigationView.getHeaderView(0);
        navHeaderImgContainer = (RelativeLayout) headerView.findViewById(R.id.navHeaderImgContainer);
        navActionPhone = (RelativeLayout) headerView.findViewById(R.id.navActionPhone);
        navActionMail = (RelativeLayout) headerView.findViewById(R.id.navActionMail);
        navActionWeb = (RelativeLayout) headerView.findViewById(R.id.navActionWeb);
        navHeaderImgContainer.setOnClickListener(actionListener);
        navActionPhone.setOnClickListener(actionListener);
        navActionMail.setOnClickListener(actionListener);
        navActionWeb.setOnClickListener(actionListener);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
        navigationView.setCheckedItem(R.id.nav_home);
    }


    @Override
    public void setContentView(int layoutResID){
        drawer = (DrawerLayout) getLayoutInflater().inflate(R.layout.principal_activity, null);
        FrameLayout activityContainer = (FrameLayout) drawer.findViewById(R.id.fragment_container);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(drawer);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (navigationView.getMenu().findItem(itemId).isChecked()) {
            return true;
        }
        if (itemId == R.id.nav_overview) {
            showHomeScreen();
        }else if(itemId == R.id.nav_home){
            showDetailPoiScreen();
        }else{

        }


        drawer.closeDrawer(GravityCompat.START);
        setTitle(navigationView.getMenu().findItem(itemId).getTitle());

        return true;
    }

    private void showActivityToolbar() {
        if (!getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        }
    }

    private void showHomeScreen() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailPoiFragment.newInstance(" ", " "))
                .commit();
    }

    private void showDetailPoiScreen(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ListPoiFragment.newInstance(" ", " "))
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (isDoubleBackToExit) {
                super.onBackPressed();
                finish();
            }
            if (!isDoubleBackToExit) {
                Toast.makeText(this, getString(R.string.re_tap_text), Toast.LENGTH_SHORT).show();
            }
            this.isDoubleBackToExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isDoubleBackToExit = false;
                }
            }, 2000);
        }
    }

    private void hideActivityToolbar() {
        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onNavOpenRequested() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    View.OnClickListener actionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.navHeaderImgContainer:
                    if (!navigationView.getMenu().findItem(R.id.nav_home).isChecked()) {
                        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
                        navigationView.setCheckedItem(R.id.nav_home);
                    }
                    break;
                case R.id.navActionPhone:

                    break;
                case R.id.navActionMail:

                    break;
                case R.id.navActionWeb:

                    break;
            }

            drawer.closeDrawer(GravityCompat.START);
        }
    };
}
