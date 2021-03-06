package nicolagigante.garage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import nicolagigante.garage.FirstTime.Intro;

/**
 * Created by Nicola on 01/06/2015.
 */
public class Redirect extends Activity {

    public static final String FIRST_RUN = "FirstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        PackageManager pm = getApplicationContext().getPackageManager();
        String installerPackageName = pm.getInstallerPackageName(getApplicationContext().getPackageName());
/*        if (installerPackageName.isEmpty()){
            Log.d("HomeCloud", "Installed via ADB, skipping.");
        }else {
           // Log.d("Whereto", installerPackageName);
        }*/
        if ("com.android.vending".equals(installerPackageName)){
            return;
        } else {
            Intent i = new Intent(this, NotSafeInstall.class);
            startActivity(i);
        }
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

