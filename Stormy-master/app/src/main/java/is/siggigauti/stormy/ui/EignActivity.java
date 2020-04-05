package is.siggigauti.stormy.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import is.siggigauti.stormy.R;

public class EignActivity extends AppCompatActivity {

    @BindView(R.id.makeOfferButton)
    Button makeOfferButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eign);
        ButterKnife.bind(this);

        String imageId1 = (String) getIntent().getSerializableExtra("image1");
        String url1 = "http://10.0.2.2:9090/Image/" + imageId1;
        String imageId2 = (String) getIntent().getSerializableExtra("image2");
        String url2 = "http://10.0.2.2:9090/Image/" + imageId2;
        String imageId3 = (String) getIntent().getSerializableExtra("image3");
        String url3 = "http://10.0.2.2:9090/Image/" + imageId3;
        System.out.println(url1);
        String[] imageUrls = new String[]{url1,url2,url3};

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        String propertyName = (String) getIntent().getSerializableExtra("propertyName");
        String propertyNumber = (String) getIntent().getSerializableExtra("propertyNumber");
        Long prize =  (Long) getIntent().getSerializableExtra("prize");
        String streetName = propertyName + " " + propertyNumber;
        System.out.println(propertyName);
        TextView street = (TextView) findViewById(R.id.street);
        street.setText(streetName);

        Long zip =  (Long) getIntent().getSerializableExtra("zip");
        String town = (String) getIntent().getSerializableExtra("town");
        String postal = zip + " " + town;
        TextView postalcode = (TextView) findViewById(R.id.postalcode);
        postalcode.setText(postal);
        String prizet = prize.toString()+" kr." ;
        TextView prizetext = (TextView) findViewById(R.id.prize);
        prizetext.setText(prizet);

       // ImageView image1 = (ImageView) findViewById(R.id.image1);
        //Picasso.get().load(url1).into(image1);


        //Upplýsingar í töflu
        Long size =  (Long) getIntent().getSerializableExtra("size");
        Long bathrooms =  (Long) getIntent().getSerializableExtra("bathrooms");
        Long rooms =  (Long) getIntent().getSerializableExtra("rooms");
        String type = (String) getIntent().getSerializableExtra("type");

        TextView sizetext = (TextView) findViewById(R.id.sizetext);
        String sizet = size.toString() + " m^2";
        sizetext.setText(sizet);
        TextView roomstext = (TextView) findViewById(R.id.roomstext);
        roomstext.setText(rooms.toString());
        TextView bathroomstext = (TextView) findViewById(R.id.bathroomstext);
        bathroomstext.setText(bathrooms.toString());
        TextView typetext = (TextView) findViewById(R.id.typetext);
        typetext.setText(type);

        //Button makeOfferButton = (Button) findViewById(R.id.makeOfferButton);

        makeOfferButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                System.out.println("ytt a make offer");
            }
        });



    }
}
