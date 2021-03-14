package com.androidprojects.demoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidprojects.demoproject.activities.admin.AddProductActivity;
import com.androidprojects.demoproject.models.ProductModel;
import com.chootdev.recycleclick.RecycleClick;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {

    RecyclerView recyclerProducts;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference productsRef;
    List<ProductModel> productModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);
        recyclerProducts = findViewById(R.id.recyclerviewProducts);
        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase = FirebaseDatabase.getInstance();
        productModelList = new ArrayList<>();
        productsRef = firebaseDatabase.getReference("Products");
        getProducts();
    }

    private void getProducts(){
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        ProductModel productModel = dataSnapshot.getValue(ProductModel.class);
                        productModel.setKey(dataSnapshot.getKey());
                        productModelList.add(productModel);
                    }

                    final MyProductsAdapter adapter = new MyProductsAdapter(productModelList, MyProductsActivity.this);
                    recyclerProducts.setAdapter(adapter);
                    RecycleClick.addTo(recyclerProducts).setOnItemLongClickListener(new RecycleClick.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClicked(RecyclerView recyclerView, final int i, View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MyProductsActivity.this);
                            builder.setTitle("Select Action");
                            builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int j) {
                                    Intent intent = new Intent(MyProductsActivity.this, AddProductActivity.class);
                                    intent.putExtra("edit", productModelList.get(i).getKey());
                                    startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, final int i) {
                                    productsRef.child(productModelList.get(i).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                productModelList.remove(i);
                                                adapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return false;
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(MyProductsActivity.this, "Network Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}