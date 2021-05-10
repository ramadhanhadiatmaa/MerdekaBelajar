package com.pintar.merdekabelajar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pintar.merdekabelajar.DetailKursusActivity;
import com.pintar.merdekabelajar.R;
import com.pintar.merdekabelajar.model.PopModel;

import java.util.List;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder> {

    Context context;

    List<PopModel> popModelList;

    public PopAdapter(Context context, List<PopModel> popModelList) {
        this.context = context;
        this.popModelList = popModelList;
    }

    @NonNull
    @Override
    public PopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PopAdapter.ViewHolder holder, int position) {

        final  PopModel model = popModelList.get(position);

        holder.judul.setText(model.getJudul());
        holder.rating.setText(model.getRating());
        holder.provider.setText(model.getProvider());
        Glide.with(context).load(model.getImageurl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String judul = model.getJudul();
                Intent intent = new Intent(context, DetailKursusActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("judul", judul);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return popModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView judul, provider, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_view);
            judul = itemView.findViewById(R.id.judul);
            provider = itemView.findViewById(R.id.provider);
            rating = itemView.findViewById(R.id.rating);

        }
    }
}
