package is.siggigauti.stormy.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Offer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MakeOfferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MakeOfferFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = MakeOfferFragment.class.getSimpleName();

    //@BindView(R.id.OfferAmount)
    //EditText input_offer;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MakeOfferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MakeOfferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MakeOfferFragment newInstance(String param1, String param2) {
        MakeOfferFragment fragment = new MakeOfferFragment();
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

        View v = inflater.inflate(R.layout.fragment_make_offer, container, false);
        Button b = (Button) v.findViewById(R.id.makeOfferButtonID);
        final TextInputLayout offerAmount = (TextInputLayout) v.findViewById(R.id.OfferAmount);
        final String propertyIDdata = getArguments().getString("data");
        final Long propertyID = Long.parseLong(propertyIDdata);
        System.out.println(propertyID);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Tilbod gert");
                Random rand = new Random();
                int offerID = rand.nextInt(1000);
                int userID= rand.nextInt(1000);
                String of = offerID +" ";
                String us = userID +"";
                System.out.println(offerAmount.getEditText().getText().toString());
                String u=offerAmount.getEditText().getText().toString();


                SaveOffer(of,propertyIDdata,u,us);
                //Offer offer = new Offer(offerID,propertyID,u,userID);

                closeFragment();
            }});
        return v;
    }
        public void closeFragment() {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

        /*Kallar á fallið SaveOffer2 í bakenda */
        public void SaveOffer(String offerID,String propertyID,String upph,String userID){
            System.out.println(propertyID);
            System.out.println(upph);
            RequestBody formBody = new FormBody.Builder()
                    .add("offerID", offerID)
                    .add("pid", propertyID)
                    .add("offerAmount", upph)
                    .build();

            Request request = new Request.Builder()
                    .url("http://10.0.2.2:9090/SaveOffer2")
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("bilad");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("vrikar");
                }
            });
        }

    private boolean isNetworkAvailable() {
        return true;
    }
}