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

import java.util.ArrayList;
import java.util.List;

import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.Property;


public class PropertyAdapter extends ArrayAdapter<Property> {

    private Context mContext;
    private List<Property> propertiesList = new ArrayList<>();

    public PropertyAdapter(@NonNull Context context, @LayoutRes List<Property> list) {
        super(context, 0 , list);
        mContext = context;
        propertiesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Property currentProperty = propertiesList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentProperty.getStreetName());

        TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        release.setText(String.valueOf(currentProperty.getPrice()));

        return listItem;
    }
}