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
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static club.thepenguins.android.utils.Constants.noInternet;
import static club.thepenguins.android.utils.Constants.noMorePosts;


public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private LinearLayoutManager LayoutManager;
    private ArrayList<Model> list;
    private PostRecyclerAdapter adapter;
    public List<Posts> mListPost;
    private SwipeRefreshLayout swipeContainer;
    private ShimmerFrameLayout loader;
    int pageNumber = 1;
    int limit;
    int perPage = 10;
    private static String TAG = "Home";


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
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


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);

        LayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);

        loader = rootView.findViewById(R.id.shimmer_view_container);

        swipeContainer = rootView.findViewById(R.id.swiperefresh);

        list = new ArrayList<>();

        getRetrofit(pageNumber, perPage, rootView.getContext());

        adapter = new PostRecyclerAdapter(list, rootView.getContext());
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1) && (dy > 0)) {
                    if (!(String.valueOf(limit).isEmpty())) {

                        getRetrofit(pageNumber, perPage, rootView.getContext());

                        recyclerView.post(() -> adapter.notifyItemInserted(adapter.getItemCount() + perPage));


                    }


                } else if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                    // Top
                }
            }
        });


        swipeContainer.setOnRefreshListener(() -> {

            pageNumber = 1;
            adapter.clear();
            getRetrofit(pageNumber, perPage, rootView.getContext());
        });


        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light);


        return rootView;
    }

    @Override
    public void onResume() {
        //loader.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        //loader.stopShimmer();
        super.onPause();
    }

    private void getRetrofit(int page, int perPage, Context context) {

        swipeContainer.setRefreshing(true);
        loader.setVisibility(View.VISIBLE);
        loader.startShimmer();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Posts>> call = service.getPostsPerPage(page, perPage);


        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                mListPost = response.body();

                Headers headers = response.headers();
                String count = headers.get("X-WP-Total");

                if (count != null) {
                    limit = Integer.parseInt(count);
                } else {
                    swipeContainer.setRefreshing(false);
                    loader.setVisibility(View.GONE);
                    Toasty.info(context, noMorePosts).show();
                }

                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {

                        list.add(new Model(Parser.unescapeEntities(response.body().get(i).getTitle().getRendered(), false),
                                response.body().get(i).getContent().getRendered(), response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl(),
                                response.body().get(i).getLinks().getSelf().get(0).getHref(),
                                response.body().get(i).getEmbedded().getAuthor().get(0).getName(),
                                response.body().get(i).getLink()));

                    }
                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                    loader.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    pageNumber = pageNumber + 1;
                }


            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Toasty.error(context, noInternet, Toast.LENGTH_LONG, true).show();
            }
        });
    }
}