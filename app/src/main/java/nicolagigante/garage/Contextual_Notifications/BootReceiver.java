package nicolagigante.garage.Contextual_Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        // TODO Auto-generated method stub
        Log.w("boot_broadcast_poc", "starting service...");
        context.startService(new Intent(context, AreWeThereIntentService.class));
    }

}
