package nicolagigante.garage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Nicola on 01/06/2015.
 */
public class Redirect extends Activity {

    public static final String FIRST_RUN = "FirstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(FIRST_RUN, true)) {
            Intent i= new Intent(this, Intro.class);
            startActivity(i);
        }
        else{
            String launchactivity = prefs.getString("LaunchActivity", "");
            Intent i = null;
            try {
                i = new Intent(this, Class.forName(launchactivity));
                startActivity(i);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            finish();
        }
    }

}

