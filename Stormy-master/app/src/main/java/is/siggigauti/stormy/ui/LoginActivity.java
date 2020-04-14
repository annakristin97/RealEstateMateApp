package is.siggigauti.stormy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPassword;

    private MainActivity mainActivity;


    @BindView(R.id.signUpButton)
    Button signUpButton;
    @BindView((R.id.loginButton))
    Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpPage();
            }
        });
        userName = (EditText) findViewById(R.id.userName_input);
        userPassword = (EditText) findViewById(R.id.userPassword_input);

    }


    private void openSignUpPage() {
        System.out.println("sjomli þú ýttir á signup fragmentos");
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        //mainActivity.getUsers();

    }
}
