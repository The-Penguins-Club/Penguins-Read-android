package club.thepenguins.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAc";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager LayoutManager;
    private ArrayList<Model> list;
    private PostRecyclerAdapter adapter;
    public static List<Posts> mListPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progressbar);

        LayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);

        list = new ArrayList<>();

        getRetrofit();

        adapter = new PostRecyclerAdapter(list, MainActivity.this);

        recyclerView.setAdapter(adapter);
    }

    private void getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Posts>> call = service.getPosts();


        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                //Log.d(TAG, "onResponse: " + response.body().get();


                mListPost = response.body();
                progressBar.setVisibility(View.GONE);
                for (int i = 0; i < response.body().size(); i++) {

                    // getFeaturedImage();

                    // getFeaturedImage(response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref());

                    list.add(new Model(response.body().get(i).getTitle().getRendered(), response.body().get(i).getContent().getRendered(), response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl(), response.body().get(i).getContent().getRendered()));

                    Log.d(TAG, "onResponse: " + response.body().get(i).getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

                Log.d(TAG, "onFailure: ", t);
            }
        });
    }


}