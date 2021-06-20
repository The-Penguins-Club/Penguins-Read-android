package club.thepenguins.android.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import club.thepenguins.android.R;

public class PostFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public PostFragment() {
        // Required empty public constructor
    }


    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_post, container, false);


        /*
        ImageView imageView = rootView.findViewById(R.id.postImage);

        Picasso.get()
                .load(mParam2)
                .placeholder(R.color.preloadColor)
                .fit()
                .centerCrop()
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(mParam2)
                                .placeholder(R.color.preloadColor)
                                .fit()
                                .centerCrop()
                                .into(imageView);
                    }
                });

         */

        Document doc = Jsoup.parse(mParam1);

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

        String htmlString = doc.html();


        WebView myWebView = rootView.findViewById(R.id.webview);

        myWebView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);
        myWebView.getSettings().getJavaScriptEnabled();


        return rootView;
    }


}