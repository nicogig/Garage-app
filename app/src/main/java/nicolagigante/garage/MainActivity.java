package nicolagigante.garage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import nicolagigante.garage.Home_Wizard.Athmo_Wizard_Homescreen;


public class MainActivity extends Activity {

    public static final String FIRST_RUN_ATHMOS = "FirstRunAthmos";
    public String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Hello", "In the onCreate ");
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("Name", "");
        String surname = prefs.getString("Surname", "");
        TextView info_text = (TextView)findViewById(R.id.info_text);
        String hi = getString(R.string.himain);
        info_text.setText( hi + " " + name + "!");
        TextView garage_name = (TextView)findViewById(R.id.textView6);
        String open = getString(R.string.garagedoor);
        String garagename = prefs.getString("GarageName", "");
        garage_name.setText(open + " " + garagename);
    }

    @Override
    public void onPause(){
        super.onPause();
       // this.finish();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        /*Intent i = new Intent(this, MainActivity.class);
        startActivity(i);*/
    }

    @Override
    public void onBackPressed(){
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void goToSettings (View view){
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }



    public void doOpen(View view){
        new MyAsyncTask().execute("hey");
    }

    public void doOpenAthmos(View view){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(FIRST_RUN_ATHMOS, true)) {
            Intent i = new Intent(this, Athmo_Wizard_Homescreen.class);
            startActivity(i);
        } else {
            Intent i = new Intent(this, Athmo.class);
            startActivity(i);
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(nicolagigante.garage.MainActivity.this);
        String ip = prefs.getString("IP", "");
        String password = prefs.getString("Pass", "");

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result) {
            if (status == "done") {
                Snackbar
                        .make(findViewById(R.id.activity_main), R.string.snackbar_text, Snackbar.LENGTH_SHORT)
                        .show();
                status = "";
            } else {
                Snackbar
                        .make(findViewById(R.id.activity_main), R.string.snackbar_text_cantsend, Snackbar.LENGTH_LONG)
                        .show();
            }

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        public void postData(String valueIWantToSend) {


            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://" + ip +"/LetMeIn.html");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("message",
                        password));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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
