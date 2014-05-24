package id.azura.map;

import id.azura.map.helper.ConnectionDetector;
import id.azura.map.helper.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class Splash extends Activity {
	ConnectionDetector cd;
	final String url = "http://192.168.43.4/api/pull.php";
	JSONArray markers = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		cd = new ConnectionDetector(getApplicationContext());

		new grabAsTa().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class grabAsTa extends AsyncTask<String, String, JSONObject> {
		ProgressDialog dialog = new ProgressDialog(Splash.this);
		Context c = Splash.this;

		@Override
		protected void onPreExecute() {
			// or show splash here!
			dialog.setMessage("Grabbing Markers!");
			dialog.show();
			super.onPreExecute();
		}

		protected JSONObject doInBackground(String... arg) {
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(url);
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			dialog.dismiss();
			super.onPostExecute(json);
			Log.e("sd",""+json.toString());
			Intent i = new Intent(c, Map.class);
			i.putExtra("json", json.toString());
			startActivity(i);

		}

	}

}
