package nicolagigante.garage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


public class Reset_Dialog extends DialogFragment {

    public Reset_Dialog(){}

    private Context mContext;

    public Reset_Dialog(Context context){
        mContext = context;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_reset_title)
                .setMessage(R.string.dialog_reset_message)
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("FirstRun", true);
                        editor.apply();
                        editor.putString("Name", "");
                        editor.apply();
                        editor.putString("Surname", "");
                        editor.apply();
                        editor.putString("LaunchActivity", "nicolagigante.garage.MainActivity");
                        editor.apply();
                        editor.putString("IP", "");
                        editor.apply();
                        editor.putString("Pass", "");
                        editor.apply();
                        editor.putBoolean("FirstRunAthmos", true);
                        editor.apply();
                        editor.putString("IPAthmo", "");
                        editor.apply();
                        Intent i = new Intent(mContext, Intro.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();

    }
}
