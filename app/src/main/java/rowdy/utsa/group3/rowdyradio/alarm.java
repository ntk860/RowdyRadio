package rowdy.utsa.group3.rowdyradio;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link alarm.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link alarm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class alarm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;

    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public alarm() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment alarm.
     */
    // TODO: Rename and change types and number of parameters
    public static alarm newInstance(String param1, String param2) {
        alarm fragment = new alarm();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        alarmTimePicker = (TimePicker) view.findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) view.findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) view.findViewById(R.id.alarmToggle);
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        // Find the ListView resource.
        mainListView = (ListView) view.findViewById( R.id.alarmListView );

        // Create and populate a List of planet names.
        //String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
        //        "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        //planetList.addAll( Arrays.asList(planets) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.simplerow, R.id.rowTextView, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        //listAdapter.add( "02:00pm" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );

        // get your ToggleButton
        ToggleButton b = (ToggleButton) view.findViewById(R.id.alarmToggle);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    final int position, long arg3) {
                // TODO Auto-generated method stub

                final ImageView alarmListItemButton = (ImageView) arg1.findViewById(R.id.alarmIconOnOff);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity()); //Read Update
                alertDialog.setTitle("Edit Alarm: " + listAdapter.getItem(position));
                //alertDialog.setMessage("this is my app");

                CharSequence[] dialogOptions;

                if(alarmListItemButton.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_menu_alarms_black).getConstantState())){
                   dialogOptions = new CharSequence[]
                            {"Enable", "Delete", "Cancel"};
                } else {
                    dialogOptions = new CharSequence[]
                            {"Disable", "Delete", "Cancel"};
                }
                alertDialog.setItems(dialogOptions,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        if(alarmListItemButton.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.ic_menu_alarms_black).getConstantState())){
                                            alarmListItemButton.setImageResource(R.drawable.ic_menu_alarms_green);
                                        } else {
                                            alarmListItemButton.setImageResource(R.drawable.ic_menu_alarms_black);
                                        }
                                        break;
                                    case 1:
                                        Toast.makeText(getActivity().getApplicationContext(), listAdapter.getItem(position) + " alarm was removed", Toast.LENGTH_SHORT).show();
                                        listAdapter.remove(listAdapter.getItem(position));
                                        break;
                                    case 2:
                                        //Cancelled
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });

                alertDialog.create().show();  //<-- See This!
            }
        });

        // attach an OnClickListener
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //if (((ToggleButton) view).isChecked()) {
                Log.d("MyActivity", "Alarm On");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                //Intent myIntent = new Intent(alarm.this, AlarmReceiver.class);
                //pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);
                //alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                String hour = Integer.toString(alarmTimePicker.getCurrentHour() % 12);
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

                if(hour.equals("0")){
                    hour = "12";
                }

                String timeToInsert = hour + ":" + zero + alarmTimePicker.getCurrentMinute() + ampm;

                if(listAdapter.getPosition(timeToInsert) < 0) {
                    listAdapter.insert(timeToInsert, 0);
                } else{
                    Toast.makeText(getActivity().getApplicationContext(), "Alarm Already Exists!", Toast.LENGTH_SHORT).show();
                }

                // Set the ArrayAdapter as the ListView's adapter.
                mainListView.setAdapter( listAdapter );
                //} else {
                //    alarmManager.cancel(pendingIntent);
                //    setAlarmText("");
                //    Log.d("MyActivity", "Alarm Off");
                //}
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }



}
