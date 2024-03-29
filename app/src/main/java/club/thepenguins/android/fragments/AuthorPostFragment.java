package club.thepenguins.android.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import club.thepenguins.android.R;
import club.thepenguins.android.adapters.PostRecyclerAdapter;
import club.thepenguins.android.api.APIService;
import club.thepenguins.android.data.Model;
import club.thepenguins.android.data.Posts;
import club.thepenguins.android.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AuthorPostFragment extends Fragment {


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


    public static AuthorPostFragment newInstance(String param1, String param2) {
        AuthorPostFragment fragment = new AuthorPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AuthorPostFragment() {
        // Required empty public constructor
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


        View rootView = inflater.inflate(R.layout.fragment_author_post, container, false);

        getActivity().setTitle("Choose Fragment");


        recyclerView = rootView.findViewById(R.id.recycler_view_author);

        LayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);

        swipeContainer = rootView.findViewById(R.id.swiperefreshauthor);

        list = new ArrayList<>();

        getRetrofit(mParam1);


        adapter = new PostRecyclerAdapter(list, rootView.getContext());

        recyclerView.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(() -> {

            adapter.clear();
            getRetrofit(mParam1);
        });


        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);

        return rootView;
    }

    private void getRetrofit(String authorId) {

        swipeContainer.setRefreshing(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Posts>> call = service.getAuthorPosts(authorId);


        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                //  mListPost = response.body();
                for (int i = 0; i < response.body().size(); i++) {

                    list.add(new Model(response.body().get(i).getTitle().getRendered(), response.body().get(i).getContent().getRendered(), response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl(), response.body().get(i).getLinks().getSelf().get(0).getHref(), response.body().get(i).getEmbedded().getAuthor().get(0).getName(), response.body().get(i).getLink()));
                    //Log.d("Home", "onResponse: " + response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl());
                }
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Log.d("Home", "onFailure: ", t);
            }
        });
    }
}