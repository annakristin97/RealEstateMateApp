package is.siggigauti.stormy.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import butterknife.BindView;
import is.siggigauti.stormy.R;

public class FilterFragment extends Fragment {

    @BindView(R.id.townFilter)
    Spinner mTownFilter;

    public FilterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }
}
