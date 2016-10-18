package chryssa.aliferi.multipresentermvp.presenters.mainMap;

import com.google.android.gms.maps.GoogleMap;

import chryssa.aliferi.multipresentermvp.presenters.BaseFlow;


public abstract class MainPresenter extends BaseFlow {

    public abstract void onBackPressed();

    public abstract void cancelButtonClicked();

    public abstract void continueButtonClicked();

    public abstract void onMapReady(GoogleMap map);

    public abstract void initialize();
}
