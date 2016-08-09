package nicolagigante.garage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

/**
 * Created by Gioacchino on 09/08/2016.
 */
@SuppressLint("Override")
@TargetApi(Build.VERSION_CODES.N)
public class QSIntentService
        extends TileService {

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick() {

        // Check to see if the device is currently locked.
        boolean isCurrentlyLocked = this.isLocked();

        if (!isCurrentlyLocked) {

            Resources resources = getApplication().getResources();

            Tile tile = getQsTile();
            String tileLabel = tile.getLabel().toString();
            String tileState = (tile.getState() == Tile.STATE_ACTIVE) ?
            "active" :
                    "inactive";

            Intent intent = new Intent(getApplicationContext(),
                    QuickOpen.class);

            startActivityAndCollapse(intent);
        }
    }

}

