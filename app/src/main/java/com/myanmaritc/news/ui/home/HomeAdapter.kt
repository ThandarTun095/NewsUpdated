package com.myanmaritc.news.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmaritc.news.R
import com.myanmaritc.news.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var articleList: List<ArticlesItem> = ArrayList()

    var clickListener: OnClickListener? = null

    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    ,View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }
        lateinit var article : ArticlesItem

        fun bind(articlesItem: ArticlesItem) {

            this.article = articlesItem

            itemView.author.text = articlesItem.author
            itemView.description.text = articlesItem.description
            Picasso.get()
                .load(articlesItem.urlToImage)
                .into(itemView.newsImage)
        }

        override fun onClick(view: View?) {
            clickListener?.onClick(article)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    fun updateArticleList(articleList: List<ArticlesItem>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(article: ArticlesItem)
    }

    fun setOnClickListener(clickListener: OnClickListener) {
        this.clickListener = clickListener
    }
}