package nicolagigante.garage.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

import nicolagigante.garage.MyMarkerView;
import nicolagigante.garage.R;
import nicolagigante.garage.ReloadWebView;

/**
 * Created by Nicola on 17/1/2016.
 */

public class Athmo_Pressure extends Fragment implements OnChartValueSelectedListener, OnChartGestureListener {

    public String text;
    private String[] txtTempString;
	
	private Context mContext;
    private BarChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pres, container, false);
		chart = (BarChart) view.findViewById(R.id.chart2);
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view, "Pa");
        chart.setMarkerView(mv);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String ipathmo = prefs.getString("IPAthmo", "");
        new ParseURL().execute(new String[]{"http://"+ ipathmo +"/pres.txt"});
        final WebView webView = (WebView)view.findViewById(R.id.webView);
        webView.loadUrl("http://" + ipathmo + "/" + getString(R.string.pressurelink));
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("about:blank");

            }
        });
        webView.setBackgroundColor(Color.parseColor("#26c6da"));
       // new ReloadWebView(getActivity(), 5, webView);
        return view;
    }

	
    private void parseData(String string, View view){
        txtTempString = string.split(";");
        ArrayList<BarEntry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        int k;
        if (txtTempString.length > 48){
            k = txtTempString.length - 48;
        } else {
            k = 0;
        }
        int temp=0;
        for (int i = k; i < txtTempString.length; i++) {
            yVals.add(new BarEntry(Float.parseFloat(txtTempString[i]), temp));
            xVals.add(String.valueOf(temp));
            temp++;
        }

        BarDataSet set1 = new BarDataSet(yVals , "");
        set1.setColor(Color.parseColor("#FFFFFF"));
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(xVals, dataSets);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        YAxis rightAxis = chart.getAxisRight();
        XAxis xAxis = chart.getXAxis();
        leftAxis.setGridColor(Color.parseColor("#26c6da"));
        leftAxis.setTextColor(Color.parseColor("#00000000"));
        rightAxis.setTextColor(Color.parseColor("#00000000"));
        rightAxis.setGridColor(Color.parseColor("#26c6da"));
        rightAxis.setAxisLineColor(Color.parseColor("#26c6da"));
        xAxis.setTextColor(Color.parseColor("#FFFFFF"));
        xAxis.setAxisLineColor(Color.parseColor("#26c6da"));
        xAxis.setGridColor(Color.parseColor("#26c6da"));
        chart.setData(data);
        chart.setBackgroundColor(Color.parseColor("#26c6da"));
        chart.setDescription("");
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setPinchZoom(true);
        chart.setNoDataText("");
        chart.setNoDataTextDescription("");
        set1.setValueTextColor(Color.parseColor("#00000000"));
        set1.setValueTextSize(10);
        chart.animateY(1000, Easing.EasingOption.Linear);
        chart.setOnChartValueSelectedListener(this);
        chart.setOnChartGestureListener(this);
        chart.getData().setHighlightEnabled(true);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Activity", "Selected: " + e.toString() + ", dataSet: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START");
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            chart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }



    private class ParseURL extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuffer buffer = new StringBuffer();
            try {
                Log.d("JSwa", "Connecting to [" + strings[0] + "]");
                Document doc = Jsoup.connect(strings[0]).get();
                Log.d("JSwa", "Connected to [" + strings[0] + "]");
                text = doc.body().text(); // "An example link"
                buffer.append(text);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return text;
        }


        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            if (result != null) {
                parseData(result, getView());
            } else {            Snackbar
                    .make(getView().findViewById(R.id.fragment_pres), R.string.snackbar_text_nodata, Snackbar.LENGTH_LONG)
                    .show();

            }
        }

    }


}
