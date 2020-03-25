package is.siggigauti.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.FilteredProperties;
import is.siggigauti.stormy.weather.Property;
import is.siggigauti.stormy.weather.PropertyFilterRequestWrapper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private FilteredProperties mFilteredProperties;
    private PropertyAdapter mAdapter;

    @BindView(R.id.summaryLabel)
    TextView mSummaryLabel;
    @BindView(R.id.refreshImageView)
    ImageView mRefreshImageView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.propertyList)
    ListView mPropertyList;
    @BindView(R.id.filterButton)
    Button mFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mProgressBar.setVisibility(View.INVISIBLE);

        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FilterFragment fragment =  new FilterFragment();
                fm.beginTransaction().replace(R.id.container, fragment).commit();
            }
        });

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProperties();
            }
        });

        getProperties();
        mPropertyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Ýtt á eign");
                List<Property> PropertyList = mFilteredProperties.getProperties();
                Property property = PropertyList.get(i);
                System.out.println(property.streetName);
                openPropertyPage(property);


            }
        });

    }

    private void openPropertyPage(Property property) {
        Intent intent =new Intent(this, EignActivity.class);
        intent.putExtra("propertyName", property.getStreetName());
        intent.putExtra("image1",property.getImage1());
        startActivity(intent);
    }

    public void getFilteredProperties(String town, String zip, String bedrooms, String price, String size, String category, String bathrooms) {
        System.out.println("Halló");

        System.out.println(town);

        if(isNetworkAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("town", town)
                    .add("zip", zip)
                    .add("bedrooms", bedrooms)
                    .add("price", price)
                    .add("size", size)
                    .add("category", category)
                    .add("bathrooms", bathrooms)
                    .build();

            Request request = new Request.Builder()
                    .url("http://10.0.2.2:9090/search")
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mFilteredProperties = parsePropertyListDetails(jsonData);
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
        }
        else {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

    public void getProperties() {
        System.out.println("Við erum hér");

        if(isNetworkAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:9090/seeAll")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mFilteredProperties = parsePropertyListDetails(jsonData);
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
        }
        else {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        if(mProgressBar.getVisibility()== View.INVISIBLE){
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }
        else {
            mRefreshImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
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
            Property property=new Property(elem.getLong("propertyID"),
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

        filteredProperties.setProperties(properties);

        return filteredProperties;
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
}
