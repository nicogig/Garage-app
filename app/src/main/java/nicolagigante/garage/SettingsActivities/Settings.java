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
import nicolagigante.garage.CustomList;
import nicolagigante.garage.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarsettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        ListView listView = (ListView) findViewById(R.id.listView);
        final String[] settingsNames = {
                getString(R.string.app_settings),
                getString(R.string.garage_settings),
                getString(R.string.athmomodify),
                getString(R.string.context_notif),
                getString(R.string.about),
        };
        Integer[] imageID = {
                R.drawable.ic_settings_white_36dp,
                R.drawable.ic_vpn_key_white_36dp,
                R.drawable.athmo_warm_homescreen,
                R.drawable.ic_location_on_white_36dp,
                R.drawable.ic_info_outline_white_36dp
        };
        final String[] settingsDesc = {
                getString(R.string.app_sett_desc),
                getString(R.string.gar_sett_desc),
                getString(R.string.ath_sett_desc),
                getString(R.string.cntx_desc),
                getString(R.string.app_name) + " " + getString(R.string.version)
        };
        CustomSettingsList adapter = new CustomSettingsList(Settings.this, settingsNames, imageID, settingsDesc);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int itemPosition = position;
                Log.d("LISTSETTINGS", String.valueOf(itemPosition));
                switch(itemPosition) {
                    case 0: Intent i0 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.ModifyAppSettings.class);
                            startActivity(i0);
                            break;
                    case 1: Intent i1 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.ModifyGarageSettings.class);
                            startActivity(i1);
                            break;
                    case 2: Intent i2 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.ModifyAthmoSettings.class);
                            startActivity(i2);
                            break;
                    case 3: if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Intent i4 = new Intent(getApplicationContext(), AllGeofencesActivity.class);
                        startActivity(i4);
                    } else {
                        Intent i4extra = new Intent(getApplicationContext(), PermissionDenied.class);
                        startActivity(i4extra);
                    }
                        break;
                    case 4: Intent i7 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.About_App.class);
                        startActivity(i7);
                        break;
                    default: Log.d("LISTSETTINGS", "Invalid");
                }
            }
        });
    }

}
