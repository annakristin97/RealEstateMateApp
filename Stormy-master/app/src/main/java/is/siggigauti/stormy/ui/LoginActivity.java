package is.siggigauti.stormy.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import is.siggigauti.stormy.R;
import is.siggigauti.stormy.ui.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText userName_input;
    private EditText userPassword_input;
    private Button loginButton;
    private Button signUpButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        /*
        userName_input = (EditText) findViewById(R.id.userName_input);
        userPassword_input = (EditText) findViewById(R.id.userPassword_input);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

         */

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpPage();
                System.out.println("Ýtt var á signup");
            }
        });
}

    private void openSignUpPage() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    }