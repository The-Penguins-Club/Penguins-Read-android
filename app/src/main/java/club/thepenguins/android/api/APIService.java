package club.thepenguins.android.api;

import java.util.List;

import club.thepenguins.android.data.Image;
import club.thepenguins.android.data.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("wp-json/wp/v2/posts?_embed")
    Call<List<Posts>> getPosts();

    @GET(".")
    Call<Image> getFeaturedImageLink();

}
