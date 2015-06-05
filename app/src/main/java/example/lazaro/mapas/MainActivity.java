package example.lazaro.mapas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener {

    //se declaran las variables
    private final LatLng micasa = new LatLng(18.338679, -100.817000);
    private GoogleMap googlemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se liga con lo grafico
        googlemap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMap();
        googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
        googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(micasa, 15));


        //Mostrar / ocultar tu ubicacion
        googlemap.setMyLocationEnabled(true);
        //Mostara / ocultar los controles de Zoom
        googlemap.getUiSettings().setZoomControlsEnabled(true);
        //Mostrar / ocultar los botones de localizacion
        googlemap.getUiSettings().setMyLocationButtonEnabled(true);
        //Mostrar / ocultar icon de compas
        googlemap.getUiSettings().setCompassEnabled(true);
        //Mostrar / ocultar evento de rotar
        googlemap.getUiSettings().setRotateGesturesEnabled(true);
        //Mostrar / ocultar funcionalidad de Zoom
        googlemap.getUiSettings().setZoomGesturesEnabled(true);

        //crear marker
        googlemap.addMarker(new MarkerOptions().position(micasa).title("Domicilio de erik lazaro").snippet("Santa teresa,gro").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        googlemap.setOnMapClickListener(this);


    }
    public void moveCamera(View view) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(18.338679, -100.817000)).zoom(15).build();
        googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void animateCamera(View view) {
        if (googlemap.getMyLocation()!= null)
            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlemap.getMyLocation().getLatitude(),googlemap.getMyLocation().getLongitude()),15));
    }

    public void addMarker(View view) {
        googlemap.addMarker(new MarkerOptions().position(new LatLng(googlemap.getCameraPosition().target.latitude, googlemap.getCameraPosition().target.longitude)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.normal) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.hibrido) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.satelital) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.terreno) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.none) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapClick(LatLng puntoPulsado) {
        googlemap.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

    }
}
