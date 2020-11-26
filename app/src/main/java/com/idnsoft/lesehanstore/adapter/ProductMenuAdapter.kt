package com.idnsoft.lesehanstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.idnsoft.lesehanstore.R
import com.idnsoft.lesehanstore.converter.getCurrency
import com.idnsoft.lesehanstore.model.Product

class ProductMenuAdapter(val listProduct: MutableList<Product>) : RecyclerView.Adapter<ProductMenuAdapter.GridViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    class GridViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.imgProduct)
        var tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var tvDetail: TextView= itemView.findViewById(R.id.tv_semi_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val produk = listProduct[position]

        Glide.with(holder.itemView.context)
            .load(listProduct[position].productPhoto)
            .apply(RequestOptions().override(350,550))
            .into(holder.imgPhoto)

        holder.tvProductName.text =produk.productName
        holder.tvDetail.text=produk.productDescription
        holder.tvPrice.text=getCurrency(produk.productPrice)
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listProduct[holder.adapterPosition])}

    }


    override fun getItemCount(): Int {
        return listProduct.size
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }
}