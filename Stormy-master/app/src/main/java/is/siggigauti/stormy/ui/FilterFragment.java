package is.siggigauti.stormy.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import is.siggigauti.stormy.R;
import is.siggigauti.stormy.weather.PropertyFilterRequestWrapper;

public class FilterFragment extends Fragment {

    public FilterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filters, container, false);
        Button b = (Button) v.findViewById(R.id.searchButton);
        final Spinner mTownFilter = (Spinner) v.findViewById(R.id.townFilter);
        final Spinner mZipFilter = (Spinner) v.findViewById(R.id.zipFilter);
        final Spinner mPriceFilter = (Spinner) v.findViewById(R.id.priceFilter);
        final Spinner mBedroomsFilter = (Spinner) v.findViewById(R.id.bedroomsFilter);
        final Spinner mBathroomsFilter = (Spinner) v.findViewById(R.id.bathroomsFilter);
        final Spinner mSizeFilter = (Spinner) v.findViewById(R.id.sizeFilter);
        final Spinner mCategoryFilter = (Spinner) v.findViewById(R.id.categoryFilter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Smellt á search");
                PropertyFilterRequestWrapper pfrw = new PropertyFilterRequestWrapper(
                        mTownFilter.getSelectedItem().toString(),
                        mZipFilter.getSelectedItem().toString(),
                        mBedroomsFilter.getSelectedItem().toString(),
                        mPriceFilter.getSelectedItem().toString(),
                        mSizeFilter.getSelectedItem().toString(),
                        mCategoryFilter.getSelectedItem().toString(),
                        mBathroomsFilter.getSelectedItem().toString()
                );
                MainActivity activity = (MainActivity) getActivity();
                activity.getFilteredProperties(
                        mTownFilter.getSelectedItem().toString(),
                        mZipFilter.getSelectedItem().toString(),
                        mBedroomsFilter.getSelectedItem().toString(),
                        mPriceFilter.getSelectedItem().toString(),
                        mSizeFilter.getSelectedItem().toString(),
                        mCategoryFilter.getSelectedItem().toString(),
                        mBathroomsFilter.getSelectedItem().toString()
                );
                closeFragment();
            }
        });
        return v;
    }

    public void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
