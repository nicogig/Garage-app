package nicolagigante.garage;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import nicolagigante.garage.SettingsActivities.Athmo_Settings;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarsettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    public void goToUser(View view){
        Intent i = new Intent(this, nicolagigante.garage.SettingsActivities.User_Settings.class);
        startActivity(i);
    }

    public void goToGarage(View view){
        Intent i = new Intent(this, nicolagigante.garage.SettingsActivities.Garage_Settings.class);
        startActivity(i);
    }

    public void goToAthmoSettings(View view){
        Intent i = new Intent(this, Athmo_Settings.class);
        startActivity(i);
    }

    public void goToReset(View view){
        DialogFragment newFragment = new nicolagigante.garage.SettingsActivities.Reset_Dialog(this);
        newFragment.show(getSupportFragmentManager(), "reset");
    }

    public void goToAbout(View view){
        Intent i = new Intent(this, nicolagigante.garage.SettingsActivities.About_App.class);
        startActivity(i);
    }

    public void goToNameSettings(View view){
        Intent i = new Intent(this, nicolagigante.garage.SettingsActivities.GarageNameSettings.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
