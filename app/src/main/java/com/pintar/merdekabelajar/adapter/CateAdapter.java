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
import com.pintar.merdekabelajar.DetailKategoriActivity;
import com.pintar.merdekabelajar.R;
import com.pintar.merdekabelajar.model.CateModel;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder> {

    Context context;

    List<CateModel> cateModelList;

    public CateAdapter(Context context, List<CateModel> cateModelList) {
        this.context = context;
        this.cateModelList = cateModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CateModel model = cateModelList.get(position);

        holder.tag.setText(model.getTag());

        Glide.with(context).load(model.getImageurl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String tag = model.getTag();
                Intent intent = new Intent(context, DetailKategoriActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tag", tag);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public TextView tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_cat);
            tag = itemView.findViewById(R.id.tag_cat);

        }
    }
}
