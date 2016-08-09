package nicolagigante.garage.SettingsActivities;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nicolagigante.garage.R;

/**
 * Created by nicol on 08/08/2016.
 */
public class CustomSettingsList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Integer[] imageID;
    private final String[] desc;
    public CustomSettingsList(Activity context,
                      String[] web, Integer[] imageID, String[] desc) {
        super(context, R.layout.activity_settings2, web);
        this.context = context;
        this.web = web;
        this.imageID = imageID;
        this.desc = desc;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_settings_row, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.rowTextView);
        ImageView fab = (ImageView) rowView.findViewById(R.id.imageView6);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.txtDescription);
        txtTitle.setText(web[position]);
        txtDesc.setText(desc[position]);
        fab.setImageResource(imageID[position]);
        return rowView;
    }
}
