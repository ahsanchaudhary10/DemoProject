package com.androidprojects.demoproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidprojects.demoproject.CategoryModel;
import com.androidprojects.demoproject.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    List<CategoryModel> categoryModelList;
    Context context;
    int pos = 0;

    public CategoriesAdapter(List<CategoryModel> categoryModelList, Context context) {
        this.categoryModelList = categoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item_layout, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.tvTitle.setText(categoryModelList.get(position).getCatName());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.defult_image));
        if (position == pos){
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else {
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.colorDarkGrey));
        }
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            tvTitle = itemView.findViewById(R.id.tvCatName);
        }
    }
}
