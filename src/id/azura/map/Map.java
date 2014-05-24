package id.azura.map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity {
	GoogleMap googleMap;
	MarkerOptions markerOptions;
	LatLng latLng;

	JSONObject json = null;

	static final LatLng SANSANI = new LatLng(0.455459, 101.418462);
	static final LatLng RSISLAMRIAU = new LatLng(0.454561, 101.417666);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		try {
			json = new JSONObject(getIntent().getStringExtra("json"));
		} catch (JSONException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Parse JSON Error",
					Toast.LENGTH_SHORT).show();
		}

		SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		googleMap = supportMapFragment.getMap();

		Marker rsir = googleMap.addMarker(new MarkerOptions()
				.position(RSISLAMRIAU)
				.title("RS ISLAM RIAU")
				.snippet("bla bla bla...")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));

		Marker sansani = googleMap.addMarker(new MarkerOptions()
				.position(SANSANI)
				.title("RS SANSANI")
				.snippet("bla bla bla...")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));

		googleMap
				.moveCamera(CameraUpdateFactory.newLatLngZoom(RSISLAMRIAU, 15));

	}
}
