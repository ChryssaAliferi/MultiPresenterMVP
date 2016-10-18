package chryssa.aliferi.multipresentermvp.activites;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import chryssa.aliferi.multipresentermvp.R;
import chryssa.aliferi.multipresentermvp.presenters.mainMap.CallingTaxiFlow;
import chryssa.aliferi.multipresentermvp.presenters.mainMap.MainPresenter;
import chryssa.aliferi.multipresentermvp.presenters.mainMap.MapFlow;
import chryssa.aliferi.multipresentermvp.presenters.menu.MenuPresenter;
import chryssa.aliferi.multipresentermvp.screens.CallingScreen;
import chryssa.aliferi.multipresentermvp.screens.MapScreen;
import chryssa.aliferi.multipresentermvp.screens.MenuScreen;

public class MainActivity extends AppCompatActivity implements MenuScreen, MapScreen, CallingScreen, OnMapReadyCallback, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    private FrameLayout calling_screen;
    private Toolbar toolbar;
    private MenuPresenter menuFlow;
    private MainPresenter presenter;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FrameLayout continue_button;
    private FrameLayout cancel_button;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeMapFlow();
        initializeMap();
        initializeMenuFlow();

    }

    private void initializeViews() {
        calling_screen = (FrameLayout) findViewById(R.id.calling_screen_layout);

        continue_button = (FrameLayout) findViewById(R.id.continue_button);
        continue_button.setOnClickListener(this);

        cancel_button = (FrameLayout) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(this);

        animateHideButton(0, 0, cancel_button);
    }

    @Override
    public Context getScreenContext() {
        return this;
    }

    private void initializeMapFlow() {
        goToMapFlow();
    }

    public void initializeMenuFlow() {
        menuFlow = new MenuPresenter(this);
        menuFlow.initialize();
    }

    public void goToCallingFlow() {
        if (presenter != null) {
            presenter.destroy();
        }
        presenter = new CallingTaxiFlow(this);
        presenter.initialize();
    }

    @Override
    public void goToMapFlow() {
        if (presenter != null) {
            presenter.destroy();
        }
        presenter = new MapFlow(this);
        presenter.initialize();
    }

    @Override
    public void showCallingScreen() {
        calling_screen.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCallingScreen() {
        calling_screen.setVisibility(View.GONE);
    }

    @Override
    public void initializeMap() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void initializeToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void centerMap(GoogleMap map) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.981037, 23.7361867), 16));
    }

    @Override
    public void zoomInMap() {
        map.animateCamera(CameraUpdateFactory.zoomTo(20), 1000, null);
    }

    @Override
    public void animateButton(final boolean showView, int delay, int duration) {
        if (showView) {
            animateShowButton(delay, duration, continue_button);
        } else {
            animateHideButton(delay, duration, continue_button);
        }
    }

    @Override
    public void moveToLocation(double lat, double lng) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16));
    }

    @Override
    public void animateCancelButton(final boolean showView, int delay, int duration) {
        if (showView) {
            animateShowButton(delay, duration, cancel_button);
        } else {
            animateHideButton(delay, duration, cancel_button);
        }
    }

    public void animateHideButton(int delay, int duration, final View button) {
        AnimatorSet animShowTranslate = new AnimatorSet();
        int bottomButton_To = button.getHeight();
        int alpha = 0;
        ObjectAnimator animateBottomButton = ObjectAnimator.ofFloat(button, "translationY", bottomButton_To).setDuration(duration);
        ObjectAnimator alphaBottomButton = ObjectAnimator.ofFloat(button, "alpha", alpha).setDuration(duration);
        animShowTranslate.setStartDelay(delay);
        animShowTranslate.setInterpolator(new AccelerateDecelerateInterpolator());
        animShowTranslate.playTogether(animateBottomButton, alphaBottomButton);

        animShowTranslate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                button.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setVisibility(View.GONE);
                button.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animShowTranslate.start();
    }


    public void animateShowButton(int delay, int duration, final View button) {
        AnimatorSet animShowTranslate = new AnimatorSet();
        int bottomButton_To = 0;
        int alpha = 1;
        ObjectAnimator animateBottomButton = ObjectAnimator.ofFloat(button, "translationY", bottomButton_To).setDuration(duration);
        ObjectAnimator alphaBottomButton = ObjectAnimator.ofFloat(button, "alpha", alpha).setDuration(duration);
        animShowTranslate.setStartDelay(delay);
        animShowTranslate.setInterpolator(new AccelerateDecelerateInterpolator());
        animShowTranslate.playTogether(animateBottomButton, alphaBottomButton);

        animShowTranslate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                button.setVisibility(View.VISIBLE);
                button.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animShowTranslate.start();
    }

    @Override
    public void zoomOutMap() {
        map.animateCamera(CameraUpdateFactory.zoomTo(16), 1000, null);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        presenter.onMapReady(map);
    }

    @Override
    public void initializeMenu() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void initializeNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void navigateToSelectPaymentMeans() {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("mode", 2);
        startActivity(intent);
    }

    @Override
    public void navigateToEditPaymentMeans() {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("mode", 1);
        startActivity(intent);
    }

    @Override
    public void closeMenu() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void openMenu() {
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_edit_payment) {
            menuFlow.editPaymentMeansClicked();
        } else if (id == R.id.nav_select_payment) {
            menuFlow.selectPaymentMeansClicked();
        } else if (id == R.id.nav_exit) {
            menuFlow.finishActivity();
        }
        return true;
    }

    @Override
    public void finishTheActivity() {
        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                menuFlow.closeMenu();
            } else {
                presenter.onBackPressed();
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.continue_button) {
            presenter.continueButtonClicked();
        } else if (id == R.id.cancel_button) {
            presenter.cancelButtonClicked();
        }
    }

}
