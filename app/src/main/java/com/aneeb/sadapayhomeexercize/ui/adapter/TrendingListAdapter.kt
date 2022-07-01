package com.aneeb.sadapayhomeexercize.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aneeb.sadapayhomeexercize.databinding.ListItemBinding
import com.aneeb.sadapayhomeexercize.model.Item
import com.mikhaellopez.circularimageview.CircularImageView

class TrendingListAdapter(trendingListItems: List<Item>) :
    RecyclerView.Adapter<TrendingListViewHolder>() {

    var trendingListItems = mutableListOf<Item>()

    init {
        this.trendingListItems.addAll(trendingListItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ListItemBinding.inflate(inflater, parent, false)
        return TrendingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingListViewHolder, position: Int) {
        val trendingListItem = trendingListItems[position]
        holder.binding.ivPhoto.apply {
            // Set Color
            circleColor = Color.WHITE

            // Set Border
            borderWidth = 1f
            borderColor = Color.BLACK
            // Add Shadow with default param
            shadowEnable = true
            // or with custom param
            shadowRadius = 1f
            shadowColor = Color.GRAY
            shadowGravity = CircularImageView.ShadowGravity.CENTER
        }
        holder.binding.ivPhoto.load(trendingListItem.owner?.avatarUrl)
        holder.binding.tvName.text = trendingListItem.name
        holder.binding.tvRepoName.text = trendingListItem.owner?.login
        holder.binding.tvDescription.text = trendingListItem.description
        holder.binding.tvLanguage.text = trendingListItem.language
        holder.binding.tvStarCount.text = trendingListItem.stargazersCount.toString()
        // Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return trendingListItems.size
    }
}

class TrendingListViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
