package com.example.marek.zad9;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {
    TextView dostawca;
    TextView dlugosc;
    TextView szerokosc;

    Criteria cr;
    Location loc;
    String mojDostawca;
    LocationManager mylm;

    private void odswiez() {

        mojDostawca = mylm.getBestProvider(cr, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        loc = mylm.getLastKnownLocation(mojDostawca);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dostawca = (TextView) findViewById(R.id.textView);
        dlugosc = (TextView) findViewById(R.id.textView2);
        szerokosc = (TextView) findViewById(R.id.textView3);
        cr = new Criteria();
        mylm = (LocationManager) getSystemService(LOCATION_SERVICE);
        mojDostawca = mylm.getBestProvider(cr, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        loc = mylm.getLastKnownLocation(mojDostawca);


        dostawca.setText("dostawca: " + mojDostawca);
        dlugosc.setText("dlugosc geograficzna: " + loc.getLongitude());
        szerokosc.setText("szerokosc geograficzna: " + loc.getLatitude());




    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mylm.requestLocationUpdates(mojDostawca, 400, 1, this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mylm.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        odswiez();
        dostawca.setText("dostawca: "+mojDostawca);
        dlugosc.setText("dlugosc geograficzna: "+loc.getLongitude());
        szerokosc.setText("szerokosc geograficzna: "+loc.getLatitude());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}