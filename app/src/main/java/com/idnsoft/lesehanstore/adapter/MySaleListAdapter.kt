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

class MySaleListAdapter(val listMySale: MutableList<Product>) : RecyclerView.Adapter<MySaleListAdapter.CardViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    class CardViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        var imgMySalePhoto: ImageView = itemView.findViewById(R.id.imgMySaleProduct)
        var tvMySaleProductName: TextView = itemView.findViewById(R.id.tvMySaleNameProduct)
        var tvMySalePrice: TextView = itemView.findViewById(R.id.tvMySalePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.list_my_sale, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val produk = listMySale[position]

        Glide.with(holder.itemView.context)
            .load(listMySale[position].productPhoto)
            .apply(RequestOptions().override(350,550))
            .into(holder.imgMySalePhoto)

        holder.tvMySaleProductName.text =produk.productName
        holder.tvMySalePrice.text=getCurrency(produk.productPrice)
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listMySale[holder.adapterPosition])}

    }


    override fun getItemCount(): Int {
        return listMySale.size
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }
}