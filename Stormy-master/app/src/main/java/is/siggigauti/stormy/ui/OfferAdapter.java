package is.siggigauti.stormy.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Offer;


public class OfferAdapter extends ArrayAdapter<Offer> {

    private Context mContext;
    private List<Offer> offersList = new ArrayList<>();

    public OfferAdapter(@NonNull Context context, @LayoutRes List<Offer> list) {
        super(context, 0 , list);
        mContext = context;
        offersList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Offer currentOffer = offersList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText("Property ID: " + String.valueOf(currentOffer.getPropertyID()));

        TextView size = (TextView) listItem.findViewById(R.id.textView_size);
        size.setText("");

        TextView price = (TextView) listItem.findViewById(R.id.textView_price);
        price.setText("Offer Amount: " + String.valueOf(currentOffer.getOfferAmount()));

        return listItem;
    }
}