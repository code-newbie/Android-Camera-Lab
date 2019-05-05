package net.anandarachmat.lab.android.mycameraapp.util;

import android.hardware.Camera;

public abstract class CameraUtil {

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


    public static int getAvailableCamera() {
        return Camera.getNumberOfCameras();
    }
}
