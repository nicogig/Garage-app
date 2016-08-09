package nicolagigante.garage.SettingsActivities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import nicolagigante.garage.R;

public class ModifyGarageSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_app_settings);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarsettingsapp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //ListView code starts here
        ListView listView = (ListView) findViewById(R.id.listView2);
        final String[] web = {
                getString(R.string.servermodify),
                getString(R.string.garagenamemodify)
        };
        Integer[] imageID = {
                R.drawable.ic_wifi_tethering_white_24dp,
                R.drawable.ic_mode_edit_white_24dp
        };
        final String[] desc = {
                getString(R.string.garage_ip_desc),
                getString(R.string.garage_name_desc)
        };
        CustomAppList adapter = new CustomAppList(this, web, imageID, desc);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int itemPosition = position;
                Log.d("LISTSETTINGS", String.valueOf(itemPosition));
                switch (itemPosition){
                    case 0: Intent i0 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.Garage_Settings.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.GarageNameSettings.class);
                        startActivity(i1);
                        break;
                }
            }
        });
    }
}
