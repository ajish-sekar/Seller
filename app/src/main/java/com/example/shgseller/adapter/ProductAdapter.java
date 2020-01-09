package com.example.shgseller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        TextView textViewProductQuantity;
        ImageButton imageButtonIncreaseQuantity;
        ImageButton imageButtonDecreaseQuantity;
        ImageButton imageButtonDeleteProduct;

        ProductViewHolder(View itemView){
            super(itemView);
            this.mView = itemView;

            textViewProductTitle = mView.findViewById(R.id.textViewProductTitle);
            textViewProductPrice = mView.findViewById(R.id.textViewProductPrice);
            textViewProductDescription = mView.findViewById(R.id.textViewProductDescription);
            textViewProductCategory = mView.findViewById(R.id.textViewProductCategory);
            imageViewProductImage = mView.findViewById(R.id.imageViewProductImage);
            textViewProductQuantity = mView.findViewById(R.id.textViewProductQuantity);
            imageButtonDecreaseQuantity = mView.findViewById(R.id.ImageButtonDecreaseQuantity);
            imageButtonIncreaseQuantity = mView.findViewById(R.id.ImageButtonIncreaseQuantity);
            imageButtonDeleteProduct = mView.findViewById(R.id.ImageButtonDeleteProduct);
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
        Product currentProduct =  productList.get(position);
        holder.textViewProductTitle.setText(currentProduct.getProduct_title());
        holder.textViewProductCategory.setText(currentProduct.getProduct_category());
        holder.textViewProductPrice.setText(String.valueOf(currentProduct.getProduct_price()));
        holder.textViewProductDescription.setText(currentProduct.getProduct_description());
        holder.textViewProductQuantity.setText(currentProduct.getProduct_stock());
        Glide.with(context)
                .load(currentProduct.getProduct_photo())
                .placeholder(R.drawable.ic_product)
                .into(holder.imageViewProductImage);

        holder.imageButtonIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.textViewProductQuantity.getText().toString());
                String newQuantity = Integer.toString(currentQuantity+1);
                holder.textViewProductQuantity.setText(newQuantity);
            }
        });

        holder.imageButtonDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.textViewProductQuantity.getText().toString());
                if(currentQuantity==0)
                    return;
                String newQuantity = Integer.toString(currentQuantity-1);
                holder.textViewProductQuantity.setText(newQuantity);
            }
        });

        holder.imageButtonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public  int getItemCount(){
        return  productList.size();
    }
}
