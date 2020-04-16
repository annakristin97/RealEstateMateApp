package is.siggigauti.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Property;
import is.siggigauti.stormy.weather.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PropertyActivity extends AppCompatActivity {

    @BindView(R.id.makeOfferButton)
    Button makeOfferButton;

    @BindView(R.id.congratulations) TextView congratulations;
    @BindView(R.id.toLogin) Button toLogin;
    @BindView(R.id.youHaveToLogIn)
    TextView youHaveToLogIn;

    private Property property;
    public Long propertyID;

    public static final String TAG = PropertyActivity.class.getSimpleName();
    private User user;
    private SharedPreferences mPrefs;
    final String PREFERENCE_STRING = "LoggedInUser";
    private int userID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ButterKnife.bind(this);

        mPrefs =  getSharedPreferences(PREFERENCE_STRING, MODE_PRIVATE);
        user = new User("TempUser", "temppass", "temp@mail.com");
        String json = mPrefs.getString("LoggedInUser", "");
        if (json == null)
            System.out.println("hdfka;dflkajsf;lkajdsf;laksdjf;adslkfjas;lfkja;dlfkfjasf;l");
        System.out.println(json);
//                                User obj = gson.fromJson(json, MyObject.class);
        try{
            parseUserData(json);
        }catch (JSONException e){
            Log.e(TAG, "JSON caught: ", e);
            //goToLogin();
        }

        toLogin.setVisibility(View.VISIBLE);
        toLogin.setText("Home");
        toLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMain();
            }
        });

        String imageId1 = (String) getIntent().getSerializableExtra("image1");
        String url1 = "http://10.0.2.2:9090/Image/" + imageId1;
        String imageId2 = (String) getIntent().getSerializableExtra("image2");
        String url2 = "http://10.0.2.2:9090/Image/" + imageId2;
        String imageId3 = (String) getIntent().getSerializableExtra("image3");
        String url3 = "http://10.0.2.2:9090/Image/" + imageId3;
        System.out.println(url1);
        String[] imageUrls = new String[]{url1,url2,url3};

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        propertyID = (Long) getIntent().getSerializableExtra("propertyID");
        String propertyName = (String) getIntent().getSerializableExtra("propertyName");
        String propertyNumber = (String) getIntent().getSerializableExtra("propertyNumber");
        Long prize =  (Long) getIntent().getSerializableExtra("prize");

        System.out.println(prize);
        String streetName = propertyName + " " + propertyNumber;
        System.out.println(propertyName);
        TextView street = (TextView) findViewById(R.id.street);
        street.setText(streetName);

        Long zip =  (Long) getIntent().getSerializableExtra("zip");
        String town = (String) getIntent().getSerializableExtra("town");

        String postal = zip + " " + town;
        TextView postalcode = (TextView) findViewById(R.id.postalcode);
        postalcode.setText(postal);

        String prizet = prize.toString()+" kr." ;
        TextView prizetext = (TextView) findViewById(R.id.prize);
        prizetext.setText(prizet);


        //Upplýsingar í töflu
        Long size =  (Long) getIntent().getSerializableExtra("size");
        Long bathrooms =  (Long) getIntent().getSerializableExtra("bathrooms");
        Long rooms =  (Long) getIntent().getSerializableExtra("rooms");
        String type = (String) getIntent().getSerializableExtra("type");

        TextView sizetext = (TextView) findViewById(R.id.sizetext);
        String sizet = size.toString() + " m^2";
        sizetext.setText(sizet);
        TextView roomstext = (TextView) findViewById(R.id.roomstext);
        roomstext.setText(rooms.toString());
        TextView bathroomstext = (TextView) findViewById(R.id.bathroomstext);
        bathroomstext.setText(bathrooms.toString());
        TextView typetext = (TextView) findViewById(R.id.typetext);
        typetext.setText(type);

        /*Þegar ýtt er á makeOfferButton er kallað á MakeOfferFragment og propertyID sett í bundle með*/
        makeOfferButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                System.out.println("ytt a make offer");

                mPrefs =  getSharedPreferences(PREFERENCE_STRING, MODE_PRIVATE);
                user = new User("TempUser", "temppass", "temp@mail.com");
                String json = mPrefs.getString("LoggedInUser", "");
               if(json==null) {
               }
               try{
                    parseUserData(json);
                }catch (JSONException e){
                    Log.e(TAG, "JSON caught: ", e);
                    youHaveToLogIn.setVisibility(View.VISIBLE);
                    toLogin.setVisibility(View.VISIBLE);
                    toLogin.setText("Sign in");
                    toLogin.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            goToLogin();
                        }
                    });
                }
/*
                //congratulations.setText("You have to log in to make an offer");
                   // congratulations.setTextColor(android.R.color.holo_red_light);
                    //congratulations.setVisibility(View.VISIBLE);
                    youHaveToLogIn.setVisibility(View.VISIBLE);
                    toLogin.setVisibility(View.VISIBLE);
                    toLogin.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                        goToLogin();
                        }
                        });*/
                if(user.getUserName() != "TempUser") {
                    FragmentManager fm = getSupportFragmentManager();
                    MakeOfferFragment fragment = new MakeOfferFragment();
                    Bundle data = new Bundle();//Use bundle to pass data
                    data.putString("data", propertyID.toString());//put string, int, etc in bundle with a key value
                    fragment.setArguments(data);//Finally set argument bundle to fragment

                    fm.beginTransaction().replace(R.id.activity_container, fragment).commit();
                }
            }
        });



    }
    private void parseUserData(String userData) throws JSONException {
        if (userData == null){
            goToMain();
        }
        JSONObject jsonObk= new JSONObject(userData);
        JSONObject json = jsonObk.getJSONObject("user");
        System.out.println(json);
        userID = json.getInt("id");
        user =  new User(userID, json.get("userName").toString(),
                json.get("userPassword").toString(),
                json.get("userEmail").toString());
    }
    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
}
