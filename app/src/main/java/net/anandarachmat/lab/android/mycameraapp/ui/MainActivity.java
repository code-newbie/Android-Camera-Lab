package net.anandarachmat.lab.android.mycameraapp.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.anandarachmat.lab.android.mycameraapp.R;
import net.anandarachmat.lab.android.mycameraapp.util.CameraUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// import butterknife.ButterKnife;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_HIGH_QUALITY_IMAGE = 2;

    private Button buttonTakePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ButterKnife.bind(this);
        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

        buttonTakePicture = (Button) findViewById(R.id.buttonCamera);
        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.i("A button with ID %s was clicked to say '%s'.", buttonTakePicture.getId(), buttonTakePicture.getText());
                if (checkCameraHardware(getApplicationContext())) {
                    takePicture();
                }
            }
        });
    }


    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasCameraPermissions() && hasWifiLocationPermissions() && hasGpsLocationPermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQUEST_CODE_HIGH_QUALITY_IMAGE); // your request code
    }


    private boolean hasWifiLocationPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }


    private boolean hasGpsLocationPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }


    private boolean hasCameraPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }


    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    private void takePicture() {

        if (CameraUtil.getAvailableCamera() > 0) {
            requestAppPermissions();

            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getApplicationContext(), "Button pressed !!", Toast.LENGTH_LONG).show();


                Intent intent = new Intent(getApplication(), CameraActivity.class);
                intent.putExtra("requestCode", REQUEST_CODE_HIGH_QUALITY_IMAGE);
                /*
                if (null != customerRaw) {
                    intent.putExtra(Onboard.DATA_NEW_CUSTOMER, customerRaw);
                    intent.putExtra(Onboard.DATA_NEW_CUSTOMER_ADDRESS, addressRaw);
                }
                */
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Camera / WIFI / GSM Location permission is not allowed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
