package is.siggigauti.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.FilteredProperties;
import is.siggigauti.stormy.weather.Property;
import is.siggigauti.stormy.weather.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserHomeActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private FilteredProperties mFilteredProperties;
    private PropertyAdapter mAdapter;
    private User user;
    @BindView(R.id.propertyList)
    ListView mPropertyList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        ButterKnife.bind(this);
        getSessionUser();
        getProperties();
    }

    /**
     *
     */
    private void getSessionUser(){
        Request request = new Request.Builder()
                .url("http://10.0.2.2:9090/loggedin")
                .build();

        callBackend(request);
    }

    /**
     *
     */
    private void getProperties() {
        Request request = new Request.Builder()
                .url("http://10.0.2.2:9090/seeAll")
                .build();

        callBackend(request);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!= null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }
    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    /**
     *
     * @param request
     */
    private void callBackend(Request request){
        OkHttpClient client = new OkHttpClient();

        if(isNetworkAvailable()) {
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                    try {
                        String jsonData = response.body().string();

                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            if (jsonData.trim().charAt(0) == '[')
                                mFilteredProperties = parsePropertyListDetails(jsonData);
                            if (jsonData.trim().charAt(0) == '{')
                                user = parseUserData(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSON caught: ", e);
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

    private void updateDisplay() {
        mAdapter = new PropertyAdapter(this, mFilteredProperties.getProperties());

        mPropertyList.setAdapter(mAdapter);
    }

    private FilteredProperties parsePropertyListDetails(String jsonData) throws JSONException{
        FilteredProperties filteredProperties = new FilteredProperties();

        ArrayList<Property> properties =new ArrayList<Property>();
        JSONArray array=new JSONArray(jsonData);
        for(int i=0;i<array.length();i++){
            JSONObject elem=(JSONObject)array.get(i);
            if (elem.getLong("sellerID") == 1 ) {
                Property property = new Property(elem.getLong("propertyID"),
                        elem.getLong("bedrooms"),
                        elem.getLong("bathrooms"),
                        elem.getString("streetName"),
                        elem.getString("streetNumber"),
                        elem.getLong("zip"),
                        elem.getString("town"),
                        elem.getLong("price"),
                        elem.getLong("propertySize"),
                        elem.getString("category"),
                        elem.getLong("rooms"),
                        elem.getLong("latitude"),
                        elem.getLong("longitude"),
                        elem.getLong("agentID"),
                        elem.getLong("sellerID"),
                        elem.getString("image1"),
                        elem.getString("image2"),
                        elem.getString("image3"),
                        elem.getString("image4"));
                properties.add(property);
            }
        }

        filteredProperties.setProperties(properties);

        return filteredProperties;
    }

    private User parseUserData(String userData) throws JSONException {
        JSONObject jsonObk= new JSONObject(userData);
        JSONObject json = jsonObk.getJSONObject("user");
        System.out.println(json);
        return new User(json.get("userName").toString(),
                json.get("userPassword").toString(),
                json.get("userEmail").toString());
    }


    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
