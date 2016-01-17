package nicolagigante.garage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Athmo_Wizard_Setup extends AppCompatActivity {

    public static final String FIRST_RUN_ATHMOS = "FirstRunAthmos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athmo__wizard__setup);
        EditText edtx = (EditText)(findViewById(R.id.ipeditatmo));
        edtx.setHintTextColor(getResources().getColor(R.color.white));
    }

    public void goToAllSet(View view){
        EditText edtx = (EditText)(findViewById(R.id.ipeditatmo));
        String ipathmo = edtx.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("IPAthmo", ipathmo);
        editor.apply();
        editor.putBoolean(FIRST_RUN_ATHMOS, false);
        editor.apply();
        Intent i = new Intent(this, Athmo.class);
        startActivity(i);
    }
}
