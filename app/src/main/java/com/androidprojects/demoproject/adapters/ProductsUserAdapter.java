package com.androidprojects.demoproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidprojects.demoproject.R;
import com.androidprojects.demoproject.models.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsUserAdapter extends RecyclerView.Adapter<ProductsUserAdapter.PVHolder> {

    List<ProductModel> productModelList;
    Context context;

    public ProductsUserAdapter(List<ProductModel> productModelList, Context context) {
        this.productModelList = productModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
        return new PVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PVHolder holder, int position) {
        if (productModelList.get(position).getFileUrls()!=null){
            Picasso.get().load(productModelList.get(position).getFileUrls().get(0)).placeholder(R.drawable.defult_image).into(holder.img);
        }
        holder.tvTitle.setText(productModelList.get(position).getProductName());
        holder.tvPrice.setText(productModelList.get(position).getPriceForUsers()+"$");
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class PVHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tvTitle, tvPrice;

        public PVHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
