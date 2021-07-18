package club.thepenguins.android.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import club.thepenguins.android.R;
import es.dmoral.toasty.Toasty;

import static club.thepenguins.android.utils.Constants.noImplemented;

public class AboutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }


    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        ListView listView = rootView.findViewById(R.id.aboutLV);
        String[] listItem = getResources().getStringArray(R.array.thanksTo);


        listView.setAdapter(new ArrayAdapter<>(rootView.getContext(), R.layout.thanks, R.id.people, listItem));
        listView.setDivider(null);


        Toasty.info(rootView.getContext(), noImplemented, Toast.LENGTH_LONG).show();
        return rootView;
    }
}