package controller;

import android.content.Context;

/**
 * Created by axelreinholdz on 2015-11-17.
 */
public class GPSManager {

    private double currentLatitude;
    private double currentLongitude;

    public boolean isAtRightLocation (Context context, double Latitude, double Longitude){
          boolean isAtRightLocation = false;

                getCurrentLocation(context);

                if (    (Latitude-0.001) < currentLatitude
                    && currentLatitude < (Latitude+0.001)
                    && (Longitude-0.001)<currentLongitude
                    && currentLongitude<(Longitude+0.001)   ){
                        isAtRightLocation = true;
                    }
               return isAtRightLocation;
            }

               private void getCurrentLocation(Context context){
                GPSTracker gps = new GPSTracker(context);
                if (gps.canGetLocation()) {
                        currentLatitude = gps.getLatitude();
                        currentLongitude = gps.getLongitude();
                    } else {
                                gps.showSettingsAlert();
                    }
            }
}
