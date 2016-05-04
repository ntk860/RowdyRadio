package rowdy.utsa.group3.rowdyradio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    static MediaPlayer mPlayer;
    ImageView buttonPlay;
    ImageView buttonStop;
    ImageView coverArtField;
    TextView artistNameField;
    TextView songNameField;
    TextView listenerCountField;

    String artistName;
    String songName;
    String hostName;
    String listenerCount;

    private OnFragmentInteractionListener mListener;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
            artistName = getArguments().getString("artistName");
            songName = getArguments().getString("songName");
            hostName = getArguments().getString("hostName");
            listenerCount = getArguments().getString("listenerCount");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        artistNameField = (TextView) view.findViewById(R.id.artistName);
        songNameField = (TextView) view.findViewById(R.id.songName);
        listenerCountField = (TextView) view.findViewById(R.id.listenerCount);
        coverArtField = (ImageView) view.findViewById(R.id.albumArt);

        coverArtField.setImageResource(R.drawable.def_cover_art);

        if(null == listenerCount){
            listenerCount = "1";
            artistName = "Stream Unavailable";
            songName = "Off Campus";

            // Set a default fallback stream, since UTSA stream is only available on campus
            hostName = "http://6093.live.streamtheworld.com/CBSNEWS_SC?SRC=CBS&DIST=CBSNEWS&TGT=CBSNEWSMOBILEPLAYER";
        } else {
            switch (artistName){
                case "Seatbelts":
                    coverArtField.setImageResource(R.drawable.tank);
                    break;
                case "Queen":
                    coverArtField.setImageResource(R.drawable.bohemian_rhapsody);
                    break;
                default:
                    coverArtField.setImageResource(R.drawable.def_cover_art);
                    break;
            }

        }

        Toast.makeText(getActivity().getApplicationContext(), "HOST: " + hostName, Toast.LENGTH_LONG).show();

        artistNameField.setText(artistName);
        songNameField.setText(songName);
        listenerCountField.setText("Listeners: " + listenerCount);

        final String streamUrl = hostName;
        buttonPlay = (ImageView) view.findViewById(R.id.playBtn);
        buttonStop = (ImageView) view.findViewById(R.id.pauseBtn);
        buttonPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                buttonPlay.setVisibility(View.INVISIBLE);
                buttonStop.setVisibility(View.VISIBLE);

                mPlayer = new MediaPlayer();
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mPlayer.setDataSource(streamUrl);
                    Toast.makeText(getActivity().getApplicationContext(), "Stream is playing!", Toast.LENGTH_LONG).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mPlayer.prepare();
                } catch (IllegalStateException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getActivity().getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                }
                mPlayer.start();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mPlayer!=null && mPlayer.isPlaying()){
                    buttonPlay.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                    mPlayer.stop();
                }
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
        // TODO Auto-generated method stub
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
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
}
