package id.azura.map;

import id.azura.map.helper.ConnectionDetector;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

public class Splash extends Activity {
	ConnectionDetector cd;
	
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
    
    
    class grabAsTa extends AsyncTask<Void, Integer, Boolean> {
        ProgressDialog dialog = new ProgressDialog(Splash.this);

        @Override
        protected void onPreExecute() {
            //or show splash here!
            dialog.setMessage("Grabbing Markers!");
            dialog.show();
            super.onPreExecute();
        }

        protected Boolean doInBackground(Void... values) {
                //magician at work!
                return true;
             }


            @Override
            protected void onProgressUpdate(Integer... values) {
                //insult user here
            }
    }

}
