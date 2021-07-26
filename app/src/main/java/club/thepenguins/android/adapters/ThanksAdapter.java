package club.thepenguins.android.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import club.thepenguins.android.R;
import es.dmoral.toasty.Toasty;

public class ThanksAdapter extends RecyclerView.Adapter<ThanksAdapter.ViewHolder> {


    private ArrayList<String> data;
    private Context ctx;

    public ThanksAdapter(ArrayList<String> list, Context context) {
        this.data = list;
        this.ctx = context;
    }

    @NonNull
    @Override
    public ThanksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thanks, parent, false);

        return new ThanksAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ThanksAdapter.ViewHolder holder, int position) {

        final String object = data.get(position);

        holder.name.setText(object);
        holder.name.setOnClickListener(v -> Toasty.success(ctx, object, Toast.LENGTH_SHORT, false).show());

    }

    public void clear() {

        data.clear();

        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.people);
        }
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }


}