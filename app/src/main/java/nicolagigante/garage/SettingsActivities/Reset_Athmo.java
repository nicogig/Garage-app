package nicolagigante.garage.SettingsActivities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nicolagigante.garage.R;

public class Reset_Athmo extends AppCompatActivity {
    public String status;
    public String sent;
    public String cannotsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__athmo);
        new MyAsyncTask().execute("hey");
    }

    public void goBack(View view){
        this.finish();
    }


    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(nicolagigante.garage.SettingsActivities.Reset_Athmo.this);
        String ip = prefs.getString("IPAthmo", "");

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result) {
            if (status == "done") {
                TextView tv = (TextView)findViewById(R.id.textView13);
                sent = getString(R.string.command_sent);
                tv.setText(sent);
                AppCompatButton btn = (AppCompatButton)findViewById(R.id.view10);
                btn.setVisibility(View.VISIBLE);
                status = "";
            } else {
                TextView tv = (TextView)findViewById(R.id.textView13);
                cannotsend = getString(R.string.cant_send_command);
                tv.setText(cannotsend);
                AppCompatButton btn = (AppCompatButton)findViewById(R.id.view10);
                btn.setVisibility(View.VISIBLE);
            }

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        public void postData(String valueIWantToSend) {


            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://" + ip +"/reset");

            try {
                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                status = "done";

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }
}
