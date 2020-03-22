package is.siggigauti.stormy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Property;

public class EignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eign);
        //String propertyName = (String) getIntent().getSerializableExtra("PropertyObject");
        Bundle data = getIntent().getExtras();
        Property property = (Property) data.getParcelable("PropertyObject");
        //System.out.println(propertyName);
        TextView nafn = (TextView) findViewById(R.id.textView2);
        //nafn.setText(propertyName);
        nafn.setText(property.streetName);
    }
}
