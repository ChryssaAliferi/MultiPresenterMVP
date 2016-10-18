package chryssa.aliferi.multipresentermvp.presenters.mainMap;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.plus.Plus;

import chryssa.aliferi.multipresentermvp.screens.MapScreen;

public class MapFlow extends MainPresenter implements LocationListener {


    MapScreen screen;
    private GoogleApiClient client;
    private LocationRequest request;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double fusedLatitude;
    private double fusedLongitude;

    public MapFlow(MapScreen screen) {
        this.screen = screen;
    }

    @Override
    public void initialize() {
        if (checkPlayServices()) {
            startFusedLocation();
            registerRequestUpdate(this);
        }
    }

    // check if google play services is installed on the device
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(screen.getScreenContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(screen.getScreenContext(),
                        "This device is supported. Please download google play services", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(screen.getScreenContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
            }
            return false;
        }
        return true;
    }


    public void startFusedLocation() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(screen.getScreenContext()).addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnectionSuspended(int cause) {
                        }

                        @Override
                        public void onConnected(Bundle connectionHint) {

                        }
                    }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {

                        @Override
                        public void onConnectionFailed(ConnectionResult result) {

                        }
                    }).build();
            mGoogleApiClient.connect();
        } else {
            mGoogleApiClient.connect();
        }
    }

    public void stopFusedLocation() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    public void registerRequestUpdate(final LocationListener listener) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000); // every second

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, listener);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!isGoogleApiClientConnected()) {
                        mGoogleApiClient.connect();
                    }
                    registerRequestUpdate(listener);
                }
            }
        }, 1000);
    }

    public boolean isGoogleApiClientConnected() {
        return mGoogleApiClient != null && mGoogleApiClient.isConnected();
    }

    @Override
    public void onLocationChanged(Location location) {
        setFusedLatitude(location.getLatitude());
        setFusedLongitude(location.getLongitude());

        Toast.makeText(screen.getScreenContext(), "NEW LOCATION RECEIVED", Toast.LENGTH_LONG).show();

        screen.moveToLocation(getFusedLatitude(),getFusedLongitude());
        stopFusedLocation();

      // latitude.setText(getString(R.string.latitude_string) +" "+ getFusedLatitude());
     //  longitude.setText(getString(R.string.longitude_string) +" "+ getFusedLongitude());
    }

    public void setFusedLatitude(double lat) {
        fusedLatitude = lat;
    }

    public void setFusedLongitude(double lon) {
        fusedLongitude = lon;
    }

    public double getFusedLatitude() {
        return fusedLatitude;
    }

    public double getFusedLongitude() {
        return fusedLongitude;
    }

    public void onMapReady(GoogleMap map) {
        screen.centerMap(map);
    }

    @Override
    public void onBackPressed() {
        stopFusedLocation();
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
