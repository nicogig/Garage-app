package nicolagigante.garage.FirstTime;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import nicolagigante.garage.R;
import nicolagigante.garage.SettingsActivities.Reset_Athmo;

/**
 * Created by nicol on 31/07/2016.
 */
public class NoEmptyDialog extends DialogFragment {

    public NoEmptyDialog(){}

    private Context mContext;

    public NoEmptyDialog(Context context){
        mContext = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.uhoh)
                .setMessage(R.string.dialog_no_empty)
                .setPositiveButton(R.string.gotit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        return builder.create();

    }
}
