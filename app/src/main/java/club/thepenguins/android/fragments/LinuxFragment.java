package club.thepenguins.android.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.jsoup.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import club.thepenguins.android.R;
import club.thepenguins.android.adapters.PostRecyclerAdapter;
import club.thepenguins.android.api.APIService;
import club.thepenguins.android.data.Model;
import club.thepenguins.android.data.Posts;
import club.thepenguins.android.utils.Constants;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static club.thepenguins.android.utils.Constants.noInternet;


public class LinuxFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private LinearLayoutManager LayoutManager;
    private ArrayList<Model> list;
    private PostRecyclerAdapter adapter;
    public static List<Posts> mListPost;
    private SwipeRefreshLayout swipeContainer;
    private ShimmerFrameLayout loader;
    private TextView textView;


    public LinuxFragment() {
        // Required empty public constructor
    }

    public static LinuxFragment newInstance(String param1, String param2) {
        LinuxFragment fragment = new LinuxFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_linux, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        loader = rootView.findViewById(R.id.shimmer_view_container);

        textView = rootView.findViewById(R.id.noPost);


        LayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);

        swipeContainer = rootView.findViewById(R.id.swiperefresh);

        list = new ArrayList<>();

        getRetrofit(rootView.getContext(), mParam1);


        adapter = new PostRecyclerAdapter(list, rootView.getContext());

        recyclerView.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                adapter.clear();
                getRetrofit(rootView.getContext(), mParam1);
                textView.setVisibility(View.GONE);
            }

        });


        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);

        return rootView;
    }

    private void getRetrofit(Context context, String id) {

        swipeContainer.setRefreshing(true);
        loader.setVisibility(View.VISIBLE);
        loader.startShimmer();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Posts>> call = service.getCategoryPosts(id);


        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                mListPost = response.body();
                for (int i = 0; i < response.body().size(); i++) {

                    list.add(new Model(Parser.unescapeEntities(response.body().get(i).getTitle().getRendered(), false), response.body().get(i).getContent().getRendered(), response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl(), response.body().get(i).getLinks().getSelf().get(0).getHref(), response.body().get(i).getEmbedded().getAuthor().get(0).getName(), response.body().get(i).getLink()));
                    //Log.d("Linux", "onResponse: " + response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl());
                }
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
                loader.setVisibility(View.GONE);

                if (list.size() == 0) {
                    textView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Toasty.error(context, noInternet, Toast.LENGTH_LONG, true).show();
            }
        });
    }
}