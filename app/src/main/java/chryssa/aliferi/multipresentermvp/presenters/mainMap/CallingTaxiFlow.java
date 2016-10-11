package chryssa.aliferi.multipresentermvp.presenters.mainMap;

import com.google.android.gms.maps.GoogleMap;

import chryssa.aliferi.multipresentermvp.screens.BaseScreen;
import chryssa.aliferi.multipresentermvp.screens.CallingScreen;

public class CallingTaxiFlow extends MainPresenter {

    CallingScreen screen;

    public CallingTaxiFlow(BaseScreen screen) {
        this.screen = (CallingScreen) screen;
    }

    @Override
    public void onBackPressed() {

    }

    public void cancelButtonClicked() {
        actionsOnFlowExit();
    }

    @Override
    public void continueButtonClicked() {
    }

    @Override
    public void onMapReady(GoogleMap map) {

    }

    @Override
    public void initialize() {
        screen.animateCancelButton(true, 400, 300);
        screen.zoomInMap();
    }


    private void actionsOnFlowExit() {
        screen.hideCallingScreen();
        screen.zoomOutMap();
        screen.animateButton(true, 300, 400);
        screen.animateCancelButton(false, 100, 300);
        screen.goToMapFlow();
    }
}
