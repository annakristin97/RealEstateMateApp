package is.siggigauti.stormy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Property;

public class EignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eign);

        String propertyName = (String) getIntent().getSerializableExtra("propertyName");
        System.out.println(propertyName);
        TextView name = (TextView) findViewById(R.id.textView2);
        name.setText(propertyName);

        String imageId = (String) getIntent().getSerializableExtra("image1");
        String url = "http://10.0.2.2:9090/Image/" + imageId;
        System.out.println(url);

        ImageView image1 = (ImageView) findViewById(R.id.image1);
        Picasso.get().load(url).into(image1);

    }
}
