package nicolagigante.garage.SettingsActivities;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import nicolagigante.garage.Contextual_Notifications.AllGeofencesActivity;
import nicolagigante.garage.Contextual_Notifications.PermissionDenied;
import nicolagigante.garage.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarsettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        ListView listView = (ListView) findViewById(R.id.listView);
        String[] settingsNames = {
                getString(R.string.usermodify),
                getString(R.string.servermodify),
                getString(R.string.garagenamemodify),
                getString(R.string.athmomodify),
                getString(R.string.context_notif),
                getString(R.string.reset),
                getString(R.string.refresh_athmo),
                getString(R.string.about),
        };
        ArrayList<String> settingsList = new ArrayList<String>();
        settingsList.addAll(Arrays.asList(settingsNames));
        ArrayAdapter listAdapter = new ArrayAdapter<String> (this, R.layout.list_settings_row, settingsList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int itemPosition = position;
                Log.d("LISTSETTINGS", String.valueOf(itemPosition));
                switch(itemPosition) {
                    case 0: Intent i0 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.User_Settings.class);
                            startActivity(i0);
                            break;
                    case 1: Intent i1 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.Garage_Settings.class);
                            startActivity(i1);
                            break;
                    case 2: Intent i2 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.GarageNameSettings.class);
                            startActivity(i2);
                            break;
                    case 3: Intent i3 = new Intent(getApplicationContext(), Athmo_Settings.class);
                            startActivity(i3);
                            break;
                    case 4: if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            Intent i4 = new Intent(getApplicationContext(), AllGeofencesActivity.class);
                            startActivity(i4);
                            } else {
                            Intent i4extra = new Intent(getApplicationContext(), PermissionDenied.class);
                            startActivity(i4extra);
                            }
                            break;
                    case 5: DialogFragment newFragment = new nicolagigante.garage.SettingsActivities.Reset_Dialog(getApplicationContext());
                            newFragment.show(getSupportFragmentManager(), "reset");
                            break;
                    case 6: DialogFragment newFragment2 = new nicolagigante.garage.SettingsActivities.Reset_Athmo_Dialog(getApplicationContext());
                            newFragment2.show(getSupportFragmentManager(), "refresh");
                            break;
                    case 7: Intent i7 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.About_App.class);
                            startActivity(i7);
                            break;
                    default: Log.d("LISTSETTINGS", "Invalid");
                }
            }
        });
    }

}
