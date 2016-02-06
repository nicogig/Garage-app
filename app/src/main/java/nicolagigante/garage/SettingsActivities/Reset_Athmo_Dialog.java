package nicolagigante.garage.SettingsActivities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nicolagigante.garage.FirstTime.Intro;
import nicolagigante.garage.R;

/**
 * Created by Nicola on 30/1/2016.
 */
public class Reset_Athmo_Dialog extends DialogFragment {

    public Reset_Athmo_Dialog(){}

    private Context mContext;

    public Reset_Athmo_Dialog(Context context){
        mContext = context;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_reset_athmo)
                .setMessage(R.string.dialog_reset_athmo_message)
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(mContext, Reset_Athmo.class);
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
