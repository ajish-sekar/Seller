package com.example.shgseller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shgseller.R;
import com.example.shgseller.models.Product;

import java.util.List;

import butterknife.BindView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> productList;
    private Context context;


    public ProductAdapter(Context context,List<Product> productList){
        this.context = context;
        this.productList = productList;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        ImageView imageViewProductImage;
        TextView textViewProductTitle;
        TextView textViewProductPrice;
        TextView textViewProductDescription;
        TextView textViewProductCategory;

        ProductViewHolder(View itemView){
            super(itemView);
            this.mView = itemView;

            textViewProductTitle = mView.findViewById(R.id.textViewProductTitle);
            textViewProductPrice = mView.findViewById(R.id.textViewProductPrice);
            textViewProductDescription = mView.findViewById(R.id.textViewProductDescription);
            textViewProductCategory = mView.findViewById(R.id.textViewProductCategory);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_row,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public  void  onBindViewHolder(ProductViewHolder holder,int position){
        holder.textViewProductTitle.setText(productList.get(position).getProduct_title());
        holder.textViewProductCategory.setText(productList.get(position).getProduct_category());
        holder.textViewProductPrice.setText(String.valueOf(productList.get(position).getProduct_price()));
        holder.textViewProductDescription.setText(productList.get(position).getProduct_description());

    }

    @Override
    public  int getItemCount(){
        return  productList.size();
    }
}
