package nicolagigante.garage;

/**
 * Created by Nicola on 4/4/2016.
 */
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
import nicolagigante.garage.SettingsActivities.Reset_Athmo;

public class NotYet_Contextual_Dialog extends DialogFragment {

    public NotYet_Contextual_Dialog(){}

    private Context mContext;

    public NotYet_Contextual_Dialog(Context context){
        mContext = context;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.gotit)
                .setMessage(R.string.context_notyet)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("UpdateContext", false);
                        editor.apply();
                        Intent i = new Intent(mContext, MainActivity.class);
                        startActivity(i);
                    }
                });
        return builder.create();

    }
}
