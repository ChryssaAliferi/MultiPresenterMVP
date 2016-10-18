package chryssa.aliferi.multipresentermvp.screens;

import com.google.android.gms.maps.GoogleMap;

public interface MapScreen extends BaseScreen {

    void initializeToolbar();

    void initializeMap();

    void centerMap(GoogleMap map);

    void goToCallingFlow();

    void showCallingScreen();

    void animateButton(final boolean showView, int delay, int duration);
    void moveToLocation(double lat, double lng);

}
