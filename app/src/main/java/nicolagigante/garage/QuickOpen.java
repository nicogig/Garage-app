package nicolagigante.garage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class QuickOpen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("QO", "onCreate" );
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_qo);
        new MyAsyncTask().execute("hey");
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, R.string.snackbar_text, duration);
        toast.show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(nicolagigante.garage.QuickOpen.this);
        if (prefs.getBoolean("QuickDiscover", true)){
            Intent i = new Intent(this, QuickOpen_Discover.class);
            startActivity(i);
            this.finish();
        }
        this.finish();
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(nicolagigante.garage.QuickOpen.this);
        String ip = prefs.getString("IP", "");
        String password = prefs.getString("Pass", "");

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result) {
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        public void postData(String valueIWantToSend) {


            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://" + ip + "/LetMeIn.html");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("message",
                        password));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }
}
