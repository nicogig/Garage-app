package nicolagigante.garage.SettingsActivities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import nicolagigante.garage.R;

/**
 * Created by nicol on 09/08/2016.
 */
public class ModifyAppSettings extends AppCompatActivity {

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
                getString(R.string.usermodify),
                getString(R.string.reset)
        };
        Integer[] imageID = {
                R.drawable.ic_account_circle_white_24dp,
                R.drawable.ic_settings_backup_restore_white_24dp
        };
        final String[] desc = {
                getString(R.string.user_desc),
                getString(R.string.reset_desc)
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
                case 0: Intent i0 = new Intent(getApplicationContext(), nicolagigante.garage.SettingsActivities.User_Settings.class);
                    startActivity(i0);
                    break;
                case 1: DialogFragment newFragment = new nicolagigante.garage.SettingsActivities.Reset_Dialog(getApplicationContext());
                    newFragment.show(getSupportFragmentManager(), "reset");
                    break;
            }
        }
    });
    }
}
