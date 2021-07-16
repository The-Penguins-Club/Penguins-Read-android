package club.thepenguins.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import club.thepenguins.android.R;
import club.thepenguins.android.adapters.CommentAdapter;
import club.thepenguins.android.api.APIService;
import club.thepenguins.android.data.CommentModel;
import club.thepenguins.android.data.Comments;
import club.thepenguins.android.data.IndividualPost;
import club.thepenguins.android.data.PostContent;
import club.thepenguins.android.utils.Constants;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static club.thepenguins.android.utils.Constants.noInternet;

public class PostActivity extends AppCompatActivity {

    private ArrayList<PostContent> postData;
    private ArrayList<CommentModel> comments;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private LinearLayoutManager layoutManager;
    private TextView textView;
    private ShimmerFrameLayout loader;
    private ImageView imageView, share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageView = findViewById(R.id.back);
        share = findViewById(R.id.share);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostActivity.super.onBackPressed();
            }
        });

        String content = getIntent().getStringExtra("content");
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String image = getIntent().getStringExtra("image");
        String link = getIntent().getStringExtra("link");

        loader = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);

        postData = new ArrayList<>();
        comments = new ArrayList<>();

        postData.clear();

        getRetrofit(content, PostActivity.this);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    Document doc = Jsoup.parseBodyFragment(postData.get(0).getContent());
                    doc.outputSettings().prettyPrint(false);

                    Elements images = doc.select("img");
                    Elements iframes = doc.select("iframe");

                    for (Element image : images) {

                        image.attr("width", "100%");
                        image.attr("height", "240px");

                    }

                    for (Element iframe : iframes) {

                        if (iframe.attr("width").isEmpty()) {
                            iframe.attr("width", "100%");
                        }

                    }

                    String htmlString = doc.select("body").html();

                    char quotes = '"';

                    String postTitle = "<h2>" + title + "</h2>" + "\n";

                    String postAuthor = "<p>" + "Author: " + author + "</p>" + "\n";

                    String postImage = "<img src= " + quotes + image + quotes + " width =" + quotes + "100%" + quotes + "/>" + "\n";

                    String newHtmlString = postImage + postTitle + postAuthor + htmlString;


                    WebView myWebView = findViewById(R.id.webview);

                    myWebView.loadDataWithBaseURL(null, newHtmlString, "text/html", "UTF-8", null);
                    myWebView.getSettings().setJavaScriptEnabled(true);

                    int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {

                        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
                            WebSettingsCompat.setForceDark(myWebView.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
                        }
                    }
                    loader.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    Toasty.error(PostActivity.this, noInternet, Toast.LENGTH_LONG, true).show();
                    Log.d("PostFragment", "run: " + e);
                }


            }
        }, 3000);

        textView = findViewById(R.id.txtview);

        recyclerView = findViewById(R.id.recyclerComment);

        layoutManager = new LinearLayoutManager(PostActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                commentAdapter = new CommentAdapter(comments, PostActivity.this);
                recyclerView.setAdapter(commentAdapter);
                String intValue = content.replaceAll("[^0-9]", "").substring(1);

                getComments(intValue);
            }
        }, 5000);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePost(PostActivity.this, link);
            }
        });


    }

    private void getRetrofit(String postUrl, Context context) {

        loader.startShimmer();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(postUrl + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<IndividualPost> call = service.getPostContent();


        call.enqueue(new retrofit2.Callback<IndividualPost>() {
            @Override
            public void onResponse(Call<IndividualPost> call, Response<IndividualPost> response) {


                if (postData.size() > 1) {
                    postData.clear();
                }


                postData.add(new PostContent(response.body().getContent().getRendered()));


            }

            @Override
            public void onFailure(Call<IndividualPost> call, Throwable t) {

                Toasty.error(context, noInternet, Toast.LENGTH_LONG, true).show();
            }
        });
    }

    public void getComments(String id) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<List<Comments>> call = service.getPostComments(id);


        call.enqueue(new retrofit2.Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                for (int i = 0; i < response.body().size(); i++) {

                    comments.add(new CommentModel(response.body().get(i).getAuthorAvatarUrls().get96(), response.body().get(i).getAuthorName(), response.body().get(i).getDate(), response.body().get(i).getContent().getRendered()));

                    System.out.println(response.body().get(i).getContent());
                }
                commentAdapter.notifyDataSetChanged();
                if (comments.size() != 0) {
                    textView.setText("Comments:");
                } else {
                    textView.setText("No Comments");
                }


            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

                Log.d("PostRecyclerAdapter", "onFailure: ", t);
            }
        });

    }

    public static void sharePost(Context context, String uri) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, uri);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

}