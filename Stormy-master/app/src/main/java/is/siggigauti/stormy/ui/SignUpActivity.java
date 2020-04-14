package is.siggigauti.stormy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;
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
    private EditText password;
    private EditText rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        signUpButton = (Button) findViewById(R.id.signUpButton);
        userName = (EditText) findViewById(R.id.input_name);
        userEmail = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        rePassword = (EditText) findViewById(R.id.input_reEnterPassword);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            System.out.println("Smellt á Búa til aðgang");
            String username = userName.getText().toString();
            String password = userName.getText().toString();
            String email = userName.getText().toString();

            System.out.println(username + " " + password + " " + email);

            SaveUser(username,email,password);
            goToLoginPage();

            }

        }

        );
    }

    public void SaveUser(String userName, String userEmail, String password){

        RequestBody formBody = new FormBody.Builder()
                .add("UserName", userName)
                .add("UserPassWord", password)
                .add("UserEmail", userEmail)
                .build();

        Request request = new Request.Builder()
                .url("http://10.0.2.2:9090/signup")
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.print(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.print(response);
            }
        });
    }
    private void goToLoginPage() {
        System.out.println("Notandi fluttur yfir á login page");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //mainActivity.getUsers();

    }
}

