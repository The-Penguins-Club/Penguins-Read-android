package club.thepenguins.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import club.thepenguins.android.R;
import club.thepenguins.android.adapters.ThanksAdapter;

public class AboutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayoutManager LayoutManager;
    private ThanksAdapter adapter;
    private ArrayList<String> list;
    private TextView textView1, textView2, textView3;
    private ImageView imageView;

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

        RecyclerView recyclerView = rootView.findViewById(R.id.aboutRV);
        String[] listItem = getResources().getStringArray(R.array.thanksTo);
        ArrayList<String> peopleList = new ArrayList<>(Arrays.asList(listItem));

        textView1 = rootView.findViewById(R.id.dev);
        textView2 = rootView.findViewById(R.id.bn);
        textView3 = rootView.findViewById(R.id.international);
        imageView = rootView.findViewById(R.id.git);


        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkOpener(rootView.getContext(), rootView.getContext().getResources().getString(R.string.github));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkOpener(rootView.getContext(), rootView.getContext().getResources().getString(R.string.github));
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkOpener(rootView.getContext(), rootView.getContext().getResources().getString(R.string.penguins));
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkOpener(rootView.getContext(), rootView.getContext().getResources().getString(R.string.inter));
            }
        });


        LayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);

        adapter = new ThanksAdapter(peopleList, rootView.getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void linkOpener(Context context, String uri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(browserIntent);
    }
}