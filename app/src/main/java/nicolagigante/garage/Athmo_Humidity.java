package nicolagigante.garage;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Nicola on 17/1/2016.
 */
public class Athmo_Humidity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        // Setup handles to view objects here
        // etFoo = (EditText) view.findViewById(R.id.etFoo);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String ipathmo = prefs.getString("IPAthmo", "");
        WebView webView = (WebView)view.findViewById(R.id.webView);
        webView.loadUrl("http://" + ipathmo + "/humidity");
        webView.setBackgroundColor(Color.parseColor("#26c6da"));
        return view;
    }

}
