package club.thepenguins.android.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import club.thepenguins.android.data.CommentModel;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {


    private ArrayList<CommentModel> data;
    private Context ctx;

    public CommentAdapter(ArrayList<CommentModel> list, Context context) {
        this.data = list;
        this.ctx = context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);

        return new CommentAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        final CommentModel object = data.get(position);

        holder.author.setText(object.getAuthor());
        holder.comment.setText("\n" + Html.fromHtml(object.getContent()));
        holder.time.setText(object.getTime());
        Picasso.get()
                .load(object.getImage())
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
                                .load(object.getImage())
                                .placeholder(R.color.shimmer_placeholder)
                                .fit()
                                .centerCrop()
                                .into(holder.imageView);
                    }
                });


    }

    public void clear() {

        data.clear();

        notifyDataSetChanged();

    }

    public void addAll(ArrayList<CommentModel> list) {

        list.addAll(list);

        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView author, comment, time;
        CardView cardView;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.commentCard);
            imageView = itemView.findViewById(R.id.commenterImage);
            author = itemView.findViewById(R.id.author);
            comment = itemView.findViewById(R.id.comment);
            time = itemView.findViewById(R.id.commentTime);
        }
    }

    public void setData(ArrayList<CommentModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}