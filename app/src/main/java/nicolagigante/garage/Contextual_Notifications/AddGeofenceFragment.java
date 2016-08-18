package nicolagigante.garage.Contextual_Notifications;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import nicolagigante.garage.R;

public class AddGeofenceFragment extends DialogFragment {

    // region Properties

    private ViewHolder viewHolder;
    private String mLatitudeText;
    private String mLongitudeText;

    private ViewHolder getViewHolder() {
        return viewHolder;
    }

    final String gpsLocationProvider = LocationManager.GPS_PROVIDER;
    final String networkLocationProvider = LocationManager.NETWORK_PROVIDER;

    AddGeofenceFragmentListener listener;

    public void setListener(AddGeofenceFragmentListener listener) {
        this.listener = listener;
    }

    // endregion

    // region Overrides

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent i4extra = new Intent(getContext(), PermissionDenied.class);
            startActivity(i4extra);
        }
        Location lastKnownLocation_byGps = locationManager.getLastKnownLocation(gpsLocationProvider);
        if (lastKnownLocation_byGps != null) {
            mLatitudeText = String.valueOf(lastKnownLocation_byGps.getLatitude());
            mLongitudeText = String.valueOf(lastKnownLocation_byGps.getLongitude());
            Log.d("Latitude", mLatitudeText);
            Log.d("Longitude", mLongitudeText);
        }
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_geofence, null);

        viewHolder = new ViewHolder();
        viewHolder.populate(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(R.string.Add, null)
                .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddGeofenceFragment.this.getDialog().cancel();
                        if (listener != null) {
                            listener.onDialogNegativeClick(AddGeofenceFragment.this);
                        }
                    }
                });

        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


              // 1. Check for valid data
              if (dataIsValid()) {
                  // 2. Create a named geofence
                  NamedGeofence geofence = new NamedGeofence();
                  geofence.name = getViewHolder().nameEditText.getText().toString();
                  geofence.latitude = Double.parseDouble(
                          getViewHolder().latitudeEditText.getText().toString());
                  geofence.longitude = Double.parseDouble(
                          getViewHolder().longitudeEditText.getText().toString());
                  geofence.radius = Float.parseFloat(
                          getViewHolder().radiusEditText.getText().toString()) * 1000.0f;

                  // 3. Call listener and dismiss or show error
                  if (listener != null) {
                      listener.onDialogPositiveClick(AddGeofenceFragment.this, geofence);
                      dialog.dismiss();
                  }
              } else {
                  // 4. Display an error message
                  showValidationErrorToast();
              }
                    }

                });

            }
        });

        return dialog;
    }

    // endregion

    // region Private

    private boolean dataIsValid() {
        boolean validData = true;

        String name = getViewHolder().nameEditText.getText().toString();
        String latitudeString = getViewHolder().latitudeEditText.getText().toString();
        String longitudeString = getViewHolder().longitudeEditText.getText().toString();
        String radiusString = getViewHolder().radiusEditText.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(latitudeString)
                || TextUtils.isEmpty(longitudeString) || TextUtils.isEmpty(radiusString)) {
            validData = false;
        } else {
            double latitude = Double.parseDouble(latitudeString);
            double longitude = Double.parseDouble(longitudeString);
            float radius = Float.parseFloat(radiusString);
            if ((latitude < Constants.Geometry.MinLatitude || latitude > Constants.Geometry.MaxLatitude)
                    || (longitude < Constants.Geometry.MinLongitude || longitude > Constants.Geometry.MaxLongitude)
                    || (radius < Constants.Geometry.MinRadius || radius > Constants.Geometry.MaxRadius)) {
                validData = false;
            }
        }

        return validData;
    }

    private void showValidationErrorToast() {
        Toast.makeText(getActivity(), getActivity().getString(R.string.Toast_Validation), Toast.LENGTH_SHORT).show();
    }

    // endregion

  // region Interfaces

  public interface AddGeofenceFragmentListener {
    void onDialogPositiveClick(DialogFragment dialog, NamedGeofence geofence);
    void onDialogNegativeClick(DialogFragment dialog);
  }

  // end region

  // region Inner classes

   class ViewHolder {
    EditText nameEditText;
    EditText latitudeEditText;
    EditText longitudeEditText;
    EditText radiusEditText;

    public void populate(View v) {
      nameEditText = (EditText) v.findViewById(R.id.fragment_add_geofence_name);
      latitudeEditText = (EditText) v.findViewById(R.id.fragment_add_geofence_latitude);
      longitudeEditText = (EditText) v.findViewById(R.id.fragment_add_geofence_longitude);
      radiusEditText = (EditText) v.findViewById(R.id.fragment_add_geofence_radius);

      latitudeEditText.setHint(String.format(v.getResources().getString(R.string.Hint_Latitude), Constants.Geometry.MinLatitude, Constants.Geometry.MaxLatitude));
      longitudeEditText.setHint(String.format(v.getResources().getString(R.string.Hint_Longitude), Constants.Geometry.MinLongitude, Constants.Geometry.MaxLongitude));
      radiusEditText.setHint(String.format(v.getResources().getString(R.string.Hint_Radius), Constants.Geometry.MinRadius, Constants.Geometry.MaxRadius));
      latitudeEditText.setText(mLatitudeText);
      longitudeEditText.setText(mLongitudeText);
    }
  }

  // endregion
}
