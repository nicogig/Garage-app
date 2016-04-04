package nicolagigante.garage.Contextual_Notifications;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.GooglePlayServicesUtil;

import nicolagigante.garage.R;

public class AllGeofencesActivity extends ActionBarActivity {

  // region Overrides
    private int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_geofences);
      final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
      upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
      if (Build.VERSION.SDK_INT >= 21) {
          getSupportActionBar().setElevation(0);
      }
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      if (Build.VERSION.SDK_INT >= 18) {
          getSupportActionBar().setHomeAsUpIndicator(upArrow);
      }
    setTitle(R.string.context_notif);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
              .add(R.id.container, new AllGeofencesFragment())
              .commit();
    }
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
          // TODO: Consider calling
          //    ActivityCompat#requestPermissions
          // here to request the missing permissions, and then overriding
          //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
          //                                          int[] grantResults)
          // to handle the case where the user grants the permission. See the documentation
          // for ActivityCompat#requestPermissions for more details.
          ActivityCompat.requestPermissions(this,
                  new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                  MY_PERMISSIONS_ACCESS_FINE_LOCATION);
      }
    GeofenceController.getInstance().init(this);
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_all_geofences, menu);

        MenuItem item = menu.findItem(R.id.action_delete_all);

        if (GeofenceController.getInstance().getNamedGeofences().size() == 0) {
            item.setVisible(false);
        }

        return true;
    }

  @Override
  protected void onResume() {
    super.onResume();

    int googlePlayServicesCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    Log.i(AllGeofencesActivity.class.getSimpleName(), "googlePlayServicesCode = " + googlePlayServicesCode);

    if (googlePlayServicesCode == 1 || googlePlayServicesCode == 2 || googlePlayServicesCode == 3) {
      GooglePlayServicesUtil.getErrorDialog(googlePlayServicesCode, this, 0).show();
    }
  }

  // endregion
}
