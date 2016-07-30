package nicolagigante.garage;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nicol on 30/07/2016.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.home_list_item, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.home_list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView6);
        FloatingActionButton fab = (FloatingActionButton) rowView.findViewById(R.id.fab);
        txtTitle.setText(web[position]);
        fab.setImageResource(imageId[position]);
        return rowView;
    }
}
