package chryssa.aliferi.multipresentermvp.presenters.mainMap;

import com.google.android.gms.maps.GoogleMap;

import chryssa.aliferi.multipresentermvp.screens.MapScreen;

public class MapFlow extends MainPresenter {

    MapScreen screen;

    public MapFlow(MapScreen screen) {
        this.screen = screen;
    }

    @Override
    public void initialize() {

    }

    public void onMapReady(GoogleMap map) {
        screen.centerMap(map);
    }

    @Override
    public void onBackPressed() {
        screen.finishTheActivity();
    }

    @Override
    public void cancelButtonClicked() {

    }

    public void continueButtonClicked() {
        screen.showCallingScreen();
        screen.animateButton(false, 200, 400);
        screen.goToCallingFlow();
    }

}
