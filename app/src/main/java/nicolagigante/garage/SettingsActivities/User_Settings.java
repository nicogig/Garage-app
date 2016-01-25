package nicolagigante.garage.SettingsActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import nicolagigante.garage.R;
import nicolagigante.garage.Settings;

public class User_Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__settings);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarusers);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        EditText edtx = (EditText)(findViewById(R.id.editname));
        EditText edtxs = (EditText)(findViewById(R.id.editsurname));
        String name = prefs.getString("Name", "");
        String surname = prefs.getString("Surname", "");
        edtx.setText(name);
        edtxs.setText(surname);
    }

    public void backToSettingsUser (View view){
        EditText edtx = (EditText)(findViewById(R.id.editname));
        EditText edtxs = (EditText)(findViewById(R.id.editsurname));
        String name = edtx.getText().toString();
        String surname = edtxs.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Name", name);
        editor.apply();
        editor.putString("Surname", surname);
        editor.apply();
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user__settings, menu);
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
