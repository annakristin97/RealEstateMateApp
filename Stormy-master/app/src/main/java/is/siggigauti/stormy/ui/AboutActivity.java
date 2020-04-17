package is.siggigauti.stormy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import is.siggigauti.stormy.R;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.goBackButton)
    Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void goHomeAgain(View view){
        //Go back to homepage
        Intent intent = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
