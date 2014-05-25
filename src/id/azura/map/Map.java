package id.azura.map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity {
	GoogleMap googleMap;
	MarkerOptions markerOptions;
	LatLng latLng;
	JSONObject json = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		googleMap = supportMapFragment.getMap();
		googleMap.setMyLocationEnabled(true);

		//googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(
				//0.524247, 101.443205)));
		//googleMap.moveCamera(CameraUpdateFactory.zoomTo(10));
		
		try {
			json = new JSONObject(getIntent().getStringExtra("json"));
			JSONArray array = json.getJSONArray("markers");
			for (int i = 0; i < array.length(); i++) {
				String id = array.getJSONObject(i).getString("id");
				String name = array.getJSONObject(i).getString("name");
				String cat = array.getJSONObject(i).getString("cat");
				Double lat = array.getJSONObject(i).getDouble("lat");
				Double lng = array.getJSONObject(i).getDouble("lng");
				String desc = array.getJSONObject(i).getString("desc");

				LatLng latlng = new LatLng(lat, lng);
				MarkerOptions mo = new MarkerOptions()
						.position(latlng)
						.title(name)
						.snippet(desc)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.bluedarker));

				googleMap.addMarker(mo);

				Log.e("id", "" + id);
				Log.e("name", "" + name);
				Log.e("cat", "" + cat);
				Log.e("lat", "" + Double.toString(lat));
				Log.e("lng", "" + Double.toString(lng));
				Log.e("desc", "" + desc);
			}
			googleMap.moveCamera(CameraUpdateFactory.zoomTo(12));
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng
						(0.524247, 101.443205)));
			
		} catch (JSONException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Parse JSON Error",
					Toast.LENGTH_SHORT).show();
			Log.e("error:", "" + e.toString());
		}

	}
}
