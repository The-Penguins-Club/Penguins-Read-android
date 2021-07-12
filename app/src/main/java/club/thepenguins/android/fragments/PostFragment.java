package club.thepenguins.android.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import club.thepenguins.android.R;
import club.thepenguins.android.api.APIService;
import club.thepenguins.android.data.IndividualPost;
import club.thepenguins.android.data.PostContent;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private ArrayList<PostContent> postData;
    private ProgressBar progressBar;

    public PostFragment() {
        // Required empty public constructor
    }


    public static PostFragment newInstance(String param1, String param2, String param3, String param4) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_post, container, false);


        progressBar = rootView.findViewById(R.id.progress);

        postData = new ArrayList<>();
        postData.clear();

        getRetrofit(mParam1);
        Log.d("TAG", "onCreateView: " + mParam1);

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

                    String postTitle = "<h2>" + mParam3 + "</h2>" + "\n";

                    String postAuthor = "<p>" + "Author: " + mParam4 + "</p>" + "\n";

                    String postImage = "<img src= " + quotes + mParam2 + quotes + " width =" + quotes + "100%" + quotes + "/>" + "\n";

                    String newHtmlString = postImage + postTitle + postAuthor + htmlString;


                    WebView myWebView = rootView.findViewById(R.id.webview);

                    myWebView.loadDataWithBaseURL(null, newHtmlString, "text/html", "UTF-8", null);
                    myWebView.getSettings().setJavaScriptEnabled(true);
                    progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    Toast.makeText(rootView.getContext(), "Please retry", Toast.LENGTH_SHORT).show();
                    Log.d("PostFragment", "run: " + e);
                }


            }
        }, 3000);


        return rootView;
    }

    private void getRetrofit(String postUrl) {

        progressBar.setVisibility(View.VISIBLE);


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

                Log.d("PostRecyclerAdapter", "onFailure: ", t);
            }
        });
    }


}