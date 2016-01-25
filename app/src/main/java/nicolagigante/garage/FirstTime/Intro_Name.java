package nicolagigante.garage.FirstTime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import nicolagigante.garage.R;

public class Intro_Name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__name);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        EditText edtx = (EditText)(findViewById(R.id.view2));
        EditText edtxs = (EditText)(findViewById(R.id.view3));
        edtx.setHintTextColor(getResources().getColor(R.color.white));
        edtxs.setHintTextColor(getResources().getColor(R.color.white));
    }

    public void goToAuth(View view){
        EditText edtx = (EditText)(findViewById(R.id.view2));
        EditText edtxs = (EditText)(findViewById(R.id.view3));
        String name = edtx.getText().toString();
        String surname = edtxs.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Name", name);
        editor.apply();
        editor.putString("Surname", surname);
        editor.apply();
        Intent i = new Intent(this, Intro_Auth.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro__name, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
