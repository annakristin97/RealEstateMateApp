package is.siggigauti.stormy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;

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




            }

                                        }

        );
    }

    private View.OnClickListener attemptSignUp() {
        //String URL_Data ="http://10.0.2.2:9090/signup";
    return null;
    }
}

