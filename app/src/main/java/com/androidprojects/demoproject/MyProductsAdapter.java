package com.androidprojects.demoproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidprojects.demoproject.models.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ProductsVHolder> {

    List<ProductModel> productModelList;
    Context context;

    public MyProductsAdapter(List<ProductModel> productModelList, Context context) {
        this.productModelList = productModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_products_layout, parent, false);
        return new ProductsVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsVHolder holder, int position) {
        if (productModelList.get(position).getFileUrls()!=null){
            Picasso.get().load(productModelList.get(position).getFileUrls().get(0))
                    .placeholder(R.drawable.defult_image).fit().into(holder.imgView);
        }
        holder.tvProductName.setText(productModelList.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ProductsVHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView tvProductName;

        public ProductsVHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.productImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
        }
    }
}
