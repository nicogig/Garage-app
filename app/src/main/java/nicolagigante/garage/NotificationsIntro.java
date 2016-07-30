package nicolagigante.garage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import nicolagigante.garage.Contextual_Notifications.AllGeofencesActivity;

public class NotificationsIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_intro);

    }

    public void skipWizard(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("UpdateContext", false);
        editor.apply();
        DialogFragment newFragment = new nicolagigante.garage.NotYet_Contextual_Dialog(this);
        newFragment.show(getSupportFragmentManager(), "notyet");
    }

    public void goToWizard(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("UpdateContext", false);
        editor.apply();
        Intent i = new Intent(this, AllGeofencesActivity.class);
        startActivity(i);
    }
}
