package rowdy.utsa.group3.rowdyradio;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmActivity extends Activity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.alarmListView );

        // Create and populate a List of planet names.
        //String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
        //        "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        //planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, R.id.rowTextView, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        //listAdapter.add( "02:00pm" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );
    }

    public void onToggleClicked(View view) {
        //if (((ToggleButton) view).isChecked()) {
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            int hour = alarmTimePicker.getCurrentHour() % 12;
            String ampm;
            String zero = "0";

            if(alarmTimePicker.getCurrentHour() >= 12){
                ampm = "pm";
            } else{
                ampm = "am";
            }

            if(alarmTimePicker.getCurrentMinute() >= 10){
                zero = "";
            }

            listAdapter.insert(hour + ":" + zero + alarmTimePicker.getCurrentMinute() + ampm, 0);

            // Set the ArrayAdapter as the ListView's adapter.
            mainListView.setAdapter( listAdapter );
        //} else {
        //    alarmManager.cancel(pendingIntent);
        //    setAlarmText("");
        //    Log.d("MyActivity", "Alarm Off");
        //}

    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }
}