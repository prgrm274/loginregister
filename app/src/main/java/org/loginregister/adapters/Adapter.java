package org.loginregister.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.loginregister.androiddev.R;
import org.loginregister.models.Item;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Item> mItems;

    public Adapter(ArrayList<Item> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int at) {
        Item currentItem = mItems.get(at);

        holder.cover.setImageResource(currentItem.getCoverImageResource());
        holder.circleImageView.setImageResource(currentItem.getCircleImageResource());
        holder.title1.setText(currentItem.getTitle1());
        holder.title2.setText(currentItem.getTitle2());
        holder.content.setText(currentItem.getContent());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cover;
        public ImageView circleImageView;
        public TextView title1;
        public TextView title2;
        public TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cover = itemView.findViewById(R.id.cover_imageview__item);
            circleImageView = itemView.findViewById(R.id.circleimageview__item);
            title1 = itemView.findViewById(R.id.title1);
            title2 = itemView.findViewById(R.id.title2);
            content = itemView.findViewById(R.id.content);
        }
    }
}
