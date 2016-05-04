package rowdy.utsa.group3.rowdyradio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class mainradio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment = null;
    Class fragmentClass = null;

    String artistName;
    String listenerCount;
    String songName;
    String hostName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainradio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        new AsyncTaskParseJson().execute();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                new AsyncTaskParseJson().execute();
                handler.postDelayed(this, 30000); //now is every 30 seconds
            }
        }, 30000); //Every 30000 ms (30 seconds)

        toolbar.setTitle("Rowdy Radio");
        setSupportActionBar(toolbar);

        fragmentClass = home.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainradio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        Bundle args = new Bundle();
        args.putString("artistName", artistName);
        args.putString("songName", songName);
        args.putString("hostName", hostName);
        args.putString("listenerCount", listenerCount);

        if (id == R.id.nav_camera) {
            fragmentClass = alarm.class;
        } else if (id == R.id.nav_home) {
            fragmentClass = home.class;
        }else if (id == R.id.nav_gallery) {
            fragmentClass = newsfeed.class;
        } else if (id == R.id.nav_slideshow) {
            //call logout method
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // you can make this class as another java file so it will be separated from your main activity.
    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here

        // My test json data, hosted on public server that is identical to the one provided by TA
        //String yourJsonStringUrl = "http://turtleboys.com/rowdyJson.php";

        String yourJsonStringUrl = "http://10.245.121.71:8000/status-json.xsl";

        // contacts JSONArray
        JSONArray dataJsonArr = null;
        JSONObject jsonRootObject;

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

                JSONObject d = json.getJSONObject("icestats");
                JSONObject c = d.getJSONObject("source");
                Log.e(TAG, c.toString());
                String host = c.getString("listenurl");
                String listeners = c.getString("listeners");
                String title = c.getString("title");
                String[] titleInfo = title.split(" - ", 2);
                String artist = titleInfo[0];
                String songName = titleInfo[1];

                // show the values in our logcat
                Log.e(TAG, "host: " + host
                        + ", listeners: " + listeners
                        + ", artist: " + artist
                        + ", song: " + songName);

                setArtistName(artist);
                setSongName(songName);
                setHostName(host);
                setListenerCount(listeners);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {}
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getListenerCount() {
        return listenerCount;
    }

    public void setListenerCount(String listenerCount) {
        this.listenerCount = listenerCount;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
