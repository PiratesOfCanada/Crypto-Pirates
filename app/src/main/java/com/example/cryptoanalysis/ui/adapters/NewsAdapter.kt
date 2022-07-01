package com.example.cryptoanalysis.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.cryptoanalysis.data.model.Coin
import com.example.cryptoanalysis.R
import com.example.cryptoanalysis.data.model.ArticlesItem

class NewsAdapter(var DataSourse : ArrayList<ArticlesItem>, val context : Context):  RecyclerView.Adapter<NewsViewHolder>() {


    private lateinit var  ItemListener : onItemClickListener;
    interface onItemClickListener{
        fun onClickListener(position : Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent,false)
        return NewsViewHolder(view, ItemListener)
    }
    override fun getItemCount(): Int {
        return DataSourse.size;
    }
    fun setItem(list: java.util.ArrayList<ArticlesItem>) {
        this.DataSourse =   list
        notifyDataSetChanged()
    }

    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
           // .placeholder(R.drawable.placeholder)
           // .error(R.drawable.error)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val itemVH = DataSourse[position]
        holder.title.text = itemVH.title

        //to load svg file
        holder.image.loadUrl(itemVH.imageUrl)
    }

    fun setItemListener(Listener: NewsAdapter.onItemClickListener){
        this.ItemListener = Listener;
    }


}
class NewsViewHolder(view: View, ItemListener: NewsAdapter.onItemClickListener) : RecyclerView.ViewHolder(view) {
    var title: TextView = view.findViewById(R.id.Title)
    var image: ImageView = view.findViewById(R.id.newsImage)

    init{
        itemView.setOnClickListener {
            ItemListener.onClickListener(adapterPosition)
        }
    }

}
