package club.thepenguins.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import club.thepenguins.android.R;
import club.thepenguins.android.activities.PostActivity;
import club.thepenguins.android.data.Model;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {


    private ArrayList<Model> data;
    private Context ctx;

    public PostRecyclerAdapter(ArrayList<Model> list, Context context) {
        this.data = list;
        this.ctx = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Model object = data.get(position);

        holder.title.setText(object.title);
        holder.subtitle.setText(Html.fromHtml(object.subtitle));
        holder.author.setText(object.author);

        Picasso.get()
                .load(object.Image)
                .placeholder(R.color.shimmer_placeholder)
                .fit()
                .centerCrop()
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(object.Image)
                                .placeholder(R.color.shimmer_placeholder)
                                .fit()
                                .centerCrop()
                                .into(holder.imageView);
                    }
                });

        /*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);
         */


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, PostActivity.class);
                intent.putExtra("content", object.Content);
                intent.putExtra("author", object.author);
                intent.putExtra("image", object.Image);
                intent.putExtra("title", object.title);
                intent.putExtra("link", object.link);
                ctx.startActivity(intent);

                /*

                FragmentManager fragmentManager = ((AppCompatActivity) ctx).getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.flContent, PostFragment.newInstance(object.Content, object.Image, object.title, object.author)).addToBackStack(null).commit();


                 */
            }
        });


    }

    public void clear() {

        data.clear();

        notifyDataSetChanged();

    }

    public void addAll(ArrayList<Model> list) {

        list.addAll(list);

        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView title, subtitle, author;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card);

            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            imageView = itemView.findViewById(R.id.Icon);
            author = itemView.findViewById(R.id.author);
        }
    }

    public void setData(ArrayList<Model> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
