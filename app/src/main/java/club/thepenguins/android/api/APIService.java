package club.thepenguins.android.api;

import java.util.List;

import club.thepenguins.android.data.Image;
import club.thepenguins.android.data.IndividualPost;
import club.thepenguins.android.data.Posts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("wp-json/wp/v2/posts?_embed")
    Call<List<Posts>> getPosts();

    @GET("wp-json/wp/v2/posts?_embed")
    Call<List<Posts>> getPostsPerPage(@Query("per_page") String count);

    @GET(".")
    Call<Image> getFeaturedImageLink();

    @GET("wp-json/wp/v2/posts?_embed")
    Call<List<Posts>> getCategoryPosts(@Query("categories") String id);

    @GET(".")
    Call<IndividualPost> getPostContent();

    @GET("wp-json/wp/v2/posts?_embed")
    Call<List<Posts>> getAuthorPosts(@Query("author") String id);

}
