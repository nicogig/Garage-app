package nicolagigante.garage;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import java.util.Random;

import nicolagigante.garage.AthmoDirect.DeviceList;
import nicolagigante.garage.Home_Wizard.Athmo_Wizard_Homescreen;


public class MainActivity extends AppCompatActivity {

    ListView list;
    public static final String FIRST_RUN_ATHMOS = "FirstRunAthmos";
    public static final String UPDATE_CONTEXT = "UpdateContext";
    public String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Hello", "In the onCreate ");
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean(UPDATE_CONTEXT, true)) {
            Intent i = new Intent(this, nicolagigante.garage.NotificationsIntro.class);
            startActivity(i);
        }
        final String[] greetings = {
                getString(R.string.greetingmain),
                getString(R.string.main_greeting1),
                getString(R.string.main_greeting2),
                getString(R.string.main_greeting3),
                getString(R.string.main_greeting4),
                getString(R.string.main_greeting5) + " " + prefs.getString("Name", "") + "!"
        };
        Random random = new Random();
        int index = random.nextInt(greetings.length);
        TextView greet = (TextView)findViewById(R.id.textView8);
        greet.setText(greetings[index]);
        TextView info_text = (TextView)findViewById(R.id.info_text);
        info_text.setText( getString(R.string.himain) + " " + prefs.getString("Name", "") + "!");
        final String[] web = {
                getString(R.string.garagedoor) + " " + prefs.getString("GarageName", ""),
                getString(R.string.chekcathmo),
                getString(R.string.checkathmodirect)
        };
        Integer[] imageId = {
                R.drawable.ic_vpn_key_white_36dp,
                R.drawable.athmo_warm_homescreen,
                R.drawable.athmo_warm_homescreen
        };
        CustomList adapter = new
                CustomList(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position){
                    case 0: doOpen();
                            break;
                    case 1: doOpenAthmos();
                            break;
                    case 2: doOpenAthmoDirect();
                            break;
                }
                //Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
       // garage_name.setText(open + " " + garagename);
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

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(getResources().getColor(color));
        ImageView imgView = (ImageView)findViewById(R.id.imageView5);
        imgView.setVisibility(View.VISIBLE);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }

    public void goToSettings (View view){
        Intent i = new Intent(this, nicolagigante.garage.SettingsActivities.Settings.class);
        startActivity(i);
    }

    public void doOpen(){
        new MyAsyncTask().execute("hey");
    }

    public void doOpenAthmos(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //FloatingActionButton fab_athmo = (FloatingActionButton)findViewById(R.id.fab2);
        if (prefs.getBoolean(FIRST_RUN_ATHMOS, true)) {
            Intent i = new Intent(this, Athmo_Wizard_Homescreen.class);
            startActivity(i);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= 21) {
                /*RelativeLayout activityMain = (RelativeLayout)findViewById(R.id.activity_main);
                LinearLayout fab2Layout = (LinearLayout)findViewById(R.id.linearLayout10);
                animateRevealColorFromCoordinates(activityMain, R.color.athmo_cyan , (int) fab2Layout.getX(), (int) fab2Layout.getY());*/
                Intent i = new Intent(this, Athmo.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this, Athmo.class);
                startActivity(i);
            }
        }
    }

    public void doOpenAthmoDirect(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //FloatingActionButton fab_athmo = (FloatingActionButton)findViewById(R.id.fab2);
        if (prefs.getBoolean(FIRST_RUN_ATHMOS, true)) {
            Intent i = new Intent(this, Athmo_Wizard_Homescreen.class);
            startActivity(i);
        } else {
                Intent i = new Intent(this, DeviceList.class);
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
