package is.siggigauti.stormy.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Property;
import is.siggigauti.stormy.weather.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.signUpButton)
    Button signUpButton;

    private EditText userEmail;
    private EditText userName;
    private EditText rePassword;
    private MainActivity mainActivity;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpButton = (Button) findViewById(R.id.signUpButton);
        userName = (EditText) findViewById(R.id.input_name);
        userEmail = (EditText) findViewById(R.id.input_email);
        EditText password = (EditText) findViewById(R.id.input_password);
        password = (EditText) findViewById(R.id.input_password);
        rePassword = (EditText) findViewById(R.id.input_reEnterPassword);

        final EditText finalPassword = password;
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalPassword != rePassword) {

                }

            System.out.println("Smellt á Búa til aðgang");
            String username = userName.getText().toString();
            String email = userEmail.getText().toString();
            String rePass = rePassword.getText().toString();
            System.out.println("username:"+ username);
            System.out.println("email:"+ email);
            System.out.println("password:"+ rePass);
            User Nyrnotandi = new User();

            Nyrnotandi.setUserEmail(email);
            Nyrnotandi.setUserName(username);
            Nyrnotandi.setUserPassword(rePass);

            SaveUser(Nyrnotandi);

            // Búið að búa til account og notandi fluttur yfir á login page
            goToLoginPage();

            }

        }

        );
    }

    private void SaveUser(User nyrnotandi) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Býr til notanda fyrir þig");
        progressDialog.show();
        String URL_DATA = "http://10.0.2.2:9090/saveUser";
        System.out.println(URL_DATA);

        RequestBody formBody = new FormBody.Builder()
                .add("userEmail", nyrnotandi.getUserEmail())
                .add("userName", nyrnotandi.getUserName())
                .add("userPassword", nyrnotandi.getUserPassword())
                .build();

        Request request = new Request.Builder()
                .url(URL_DATA)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print("Villa");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print("Positive");
            }
        });
    }


    /*
        public void SaveUser(User user){

            RequestBody formBody = new FormBody.Builder()
                    .add("userEmail", userEmail)
                    .add("userName", userName)
                    .add("userPassword", password)
                    .build();
            Request request = new Request.Builder()
                    .url("http://10.0.2.2:9090/saveUser")
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.print("Villa");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.print("Positive");
                }
            });
        }

     */
    private void goToLoginPage() {
        System.out.println("Notandi fluttur yfir á login page");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

