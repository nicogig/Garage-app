package nicolagigante.garage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nicolagigante.garage.FirstTime.Intro;

public class NotSafeInstall extends AppCompatActivity {
    public static final String FIRST_RUN = "FirstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_safe_install);
    }

    public void goToDone(View view){
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
