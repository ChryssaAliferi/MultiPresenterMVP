package chryssa.aliferi.multipresentermvp.screens;

public interface CallingScreen extends BaseScreen {

    void hideCallingScreen();

    void goToMapFlow();

    void zoomOutMap();

    void animateButton(boolean b, int i, int i1);

    void animateCancelButton(boolean b, int i, int i1);

    void zoomInMap();
}
