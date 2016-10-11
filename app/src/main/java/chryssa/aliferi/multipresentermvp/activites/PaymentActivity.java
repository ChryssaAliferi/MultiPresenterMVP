package chryssa.aliferi.multipresentermvp.activites;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import chryssa.aliferi.multipresentermvp.R;
import chryssa.aliferi.multipresentermvp.presenters.payment.EditPaymentFlow;
import chryssa.aliferi.multipresentermvp.presenters.payment.PaymentPresenter;
import chryssa.aliferi.multipresentermvp.presenters.payment.SelectPaymentFlow;
import chryssa.aliferi.multipresentermvp.screens.PaymentScreen;


public class PaymentActivity extends AppCompatActivity implements PaymentScreen, NavigationView.OnNavigationItemSelectedListener {

    public static final int EDIT_PAYMENT_MODE = 1;
    public static final int SELECT_PAYMENT_MODE = 2;

    PaymentPresenter flow;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initializeFlow();
        initializeActionBar();
    }

    public void initializeFlow() {
        int mode = getIntent().getExtras().getInt("mode");

        if (mode == PaymentActivity.EDIT_PAYMENT_MODE) {
            flow = new EditPaymentFlow(this);
        } else if (mode == PaymentActivity.SELECT_PAYMENT_MODE) {
            flow = new SelectPaymentFlow(this);
        }
    }

    @Override
    public void setTitle(String title) {
        actionBar.setTitle(title);
    }

    public void initializeActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        flow.setTitle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                flow.goToMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void finishTheActivity() {
        finish();
    }

    public Context getScreenContext() {
        return this;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }


}
